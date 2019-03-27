package org.maximkir.shcf4j.api.config;

import org.maximkir.shcf4j.api.HttpClientBaseTest;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpRequestBuilder;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.Request;
import org.maximkir.shcf4j.api.auth.AuthScope;
import org.maximkir.shcf4j.api.auth.UsernamePasswordCredentials;
import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.config.CookieSpecs;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.absent;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public abstract class HttpClientBuilderConfigTest extends HttpClientBaseTest {

    protected static final String RESOURCE_URI = "/my/resource";

    @Test(expected = Exception.class)
    public void globalRequestTimeoutTest() {

        long millisecondsDelay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                                .withFixedDelay((int) millisecondsDelay)
                ));

        HttpRequest request =
                HttpRequestBuilder
                        .GET()
                        .uri(RESOURCE_URI)
                        .build();
        RequestConfig rc =
                RequestConfig
                        .builder()
                        .socketTimeoutMilliseconds((int) millisecondsDelay / 2)
                        .build();


        execute(HOST, request, Function.identity(), null, b -> b.setDefaultRequestConfig(rc));
    }

    @Test
    public void requestInterceptorTest() {

        String injectedHeaderName = "X-Injected", injectedHeaderValue = "X-Injected-Value";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .withHeader(injectedHeaderName, equalTo(injectedHeaderValue))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );

        execute(HOST,
                HttpRequestBuilder.GET(URI.create(RESOURCE_URI)).build(),
                resp -> {
                    Assert.assertEquals(200, resp.getStatusLine().getStatusCode());
                    return null;
                },
                null,
                b -> b.addRequestInterceptor(request -> request.addHeader(injectedHeaderName, injectedHeaderValue)));
    }

    @Test
    public void requestIgnoreCookiesWithHttpClientBuilderTest() {
        String cookie = "sessionToken=abc123";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .withHeader("Cookie", absent())
                .willReturn(
                        aResponse()
                                .withHeader("Set-Cookie", cookie)
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );

        RequestConfig rc = RequestConfig.builder().cookieSpec(CookieSpecs.IGNORE_COOKIES).build();

        Request<?> request =
                Request
                        .builder()
                        .host(HOST)
                        .request(HttpRequestBuilder.GET(URI.create(RESOURCE_URI)).build())
                        .callback(resp -> {
                            assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(200);
                            return null;
                        })
                        .build();

        execute(Arrays.asList(request, request), b -> b.setDefaultRequestConfig(rc));
    }

    @Test
    public void requestStoreCookieWithHttpClientBuilderTest() {
        String cookieName = "sessionToken", cookieValue = "abc123";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .withHeader("Cookie", absent())
                .willReturn(
                        aResponse()
                                .withHeader("Set-Cookie", cookieName + "=" + cookieValue)
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );


        String subResourceUri = RESOURCE_URI + "/hello";
        instanceRule.stubFor(get(urlEqualTo(subResourceUri))
                .withHeader("Cookie", equalTo(cookieName + "=" + cookieValue))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );


        RequestConfig rc = RequestConfig.builder().cookieSpec(CookieSpecs.STANDARD_RFC_6265).build();


        Request<?> request1 =
                Request
                        .builder()
                        .host(HOST)
                        .request(HttpRequestBuilder.GET(URI.create(RESOURCE_URI)).build())
                        .callback(resp -> {
                            assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(200);
                            return null;
                        })
                        .build();

        Request<?> request2 =
                Request
                        .builder()
                        .host(HOST)
                        .request(HttpRequestBuilder.GET(URI.create(subResourceUri)).build())
                        .callback(resp -> {
                            assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(200);
                            return null;
                        })
                        .build();


        execute(Arrays.asList(request1, request2), b -> b.setDefaultRequestConfig(rc));
    }

    @Test
    public void followRedirectTest() {

        String movedResource = "/new/resource";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );

        HttpRequest request =
                HttpRequestBuilder
                        .GET()
                        .uri(RESOURCE_URI)
                        .build();

        // Enable redirects
        RequestConfig rc =
                RequestConfig
                        .builder()
                        .redirectsEnabled(true)
                        .build();


        HttpResponse resp = execute(HOST, request, Function.identity(), null, b -> b.setDefaultRequestConfig(rc));

        assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

        // Disable redirects
        RequestConfig rc2 =
                RequestConfig
                        .builder()
                        .redirectsEnabled(false)
                        .build();


        resp = execute(HOST, request, Function.identity(), null, b -> b.setDefaultRequestConfig(rc2));
        assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_MOVED_PERM);

    }


    @Test(expected = Exception.class)
    public void maxRedirectsExceededTest() {

        String movedResource1 = "/new/resource1";
        String movedResource2 = "/new/resource2";
        String movedResource3 = "/new/resource3";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource1)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource1))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource2)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource2))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource3)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource3))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );

        HttpRequest request =
                HttpRequestBuilder
                        .GET()
                        .uri(RESOURCE_URI)
                        .build();

        // Enable redirects
        RequestConfig rc =
                RequestConfig
                        .builder()
                        .redirectsEnabled(true)
                        .maxRedirects(2)
                        .build();


        execute(HOST, request, Function.identity(), null, b -> b.setDefaultRequestConfig(rc));

    }

    @Test
    public void maxRedirectsTest() {
        String movedResource1 = "/new/resource1";
        String movedResource2 = "/new/resource2";
        String movedResource3 = "/new/resource3";

        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource1)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource1))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource2)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource2))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_MOVED_PERM)
                                .withHeader("Location", HOST.getSchemeName() + "://" + HOST.getHostname() + ":" + HOST.getPort() + movedResource3)
                )
        );

        instanceRule.stubFor(get(urlEqualTo(movedResource3))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                )
        );

        HttpRequest request =
                HttpRequestBuilder
                        .GET()
                        .uri(RESOURCE_URI)
                        .build();

        // Enable redirects
        RequestConfig rc =
                RequestConfig
                        .builder()
                        .redirectsEnabled(true)
                        .maxRedirects(5)
                        .build();


        HttpResponse resp = execute(HOST, request, Function.identity(), null, b -> b.setDefaultRequestConfig(rc));
        assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
    }


    @Test
    public void basicAuthenticationAnyScopeTest() {
        String uri = "/basic/auth";
        String user = "user";
        String password = "password";

        createEndpointWithBasicAuthentication(uri, user, password);

        HttpRequest request = HttpRequestBuilder.GET(URI.create(uri)).build();
        CredentialsProvider cp = CredentialsProvider
                .builder()
                .credential(AuthScope.createAnyAuthScope(),
                        UsernamePasswordCredentials.builder().username(user).password(password).build())
                .build();


        HttpResponse response = execute(HttpClientBaseTest.HOST, request, Function.identity(), null,
                b -> b.setDefaultCredentialsProvider(cp));
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

    }


    @Test
    public void basicAuthenticationSpecificScopeTest() {
        String uri = "/basic/auth";
        String user = "user";
        String password = "password";

        createEndpointWithBasicAuthentication(uri, user, password);

        HttpRequest request = HttpRequestBuilder.GET(URI.create(uri)).build();
        CredentialsProvider cp = CredentialsProvider
                .builder()
                .credential(AuthScope.builder().host(HOST.getHostname()).scheme("basic").build(),
                        UsernamePasswordCredentials.builder().username(user).password(password).build())
                .build();

        HttpResponse response = execute(HttpClientBaseTest.HOST, request, Function.identity(), null,
                b -> b.setDefaultCredentialsProvider(cp));

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_OK);

        CredentialsProvider cp2 = CredentialsProvider
                .builder()
                .credential(AuthScope.builder().host(HOST.getHostname()).scheme("digest").build(),
                        UsernamePasswordCredentials.builder().username(user).password(password).build())
                .build();

        response = execute(HttpClientBaseTest.HOST, request, Function.identity(), null,
                b -> b.setDefaultCredentialsProvider(cp2));

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(HttpURLConnection.HTTP_UNAUTHORIZED);

    }

    private void createEndpointWithBasicAuthentication(String uri, String user, String password) {

        instanceRule.stubFor(get(urlEqualTo(uri))
                .willReturn(aResponse()
                        .withStatus(401)
                        .withHeader("WWW-Authenticate", "Basic realm=localhost")
                ));

        instanceRule.stubFor(get(urlEqualTo(uri))
                .withBasicAuth(user, password)
                .willReturn(aResponse()
                        .withStatus(200)
                ));
    }

}
