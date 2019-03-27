package org.maximkir.shcf4j.test.config;

import org.maximkir.shcf4j.test.HttpClientBaseTest;
import org.maximkir.shcf4j.test.HttpClientCommonBuilder;
import org.maximkir.shcf4j.test.HttpRequest;
import org.maximkir.shcf4j.test.HttpRequestBuilder;
import org.maximkir.shcf4j.test.Request;
import org.maximkir.shcf4j.test.client.config.CookieSpecs;
import org.maximkir.shcf4j.test.client.config.RequestConfig;
import org.maximkir.shcf4j.test.client.protocol.ClientContext;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.absent;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>RequestConfigTest</b>
 */
public abstract class RequestConfigTest extends HttpClientBaseTest {

    protected static final String RESOURCE_URI = "/my/resource";


    private void registerStubWithDelayedResponse(long delayMillis) {
        instanceRule.stubFor(get(urlEqualTo(RESOURCE_URI))
                .willReturn(
                        aResponse()
                                .withStatus(HttpURLConnection.HTTP_OK)
                                .withFixedDelay((int) delayMillis)
                )
        );
    }


    @Test(expected = Exception.class)
    public void requestTimeoutTest() {

        long millisecondsDelay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.SECONDS);
        registerStubWithDelayedResponse(millisecondsDelay);

        HttpRequest request =
                HttpRequestBuilder
                        .GET()
                        .uri(RESOURCE_URI)
                        .build();

        execute(HOST, request,
                ClientContext
                        .builder()
                        .requestConfig(
                                RequestConfig
                                        .builder()
                                        .socketTimeoutMilliseconds((int) millisecondsDelay / 2)
                                        .build())
                        .build());
    }


    @Test
    public void requestIgnoreCookiesWithClientContextTest() {
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
        ClientContext ctx = ClientContext.builder().requestConfig(rc).build();

        Request<?> request =
                Request
                        .builder()
                        .host(HOST)
                        .request(HttpRequestBuilder.GET(URI.create(RESOURCE_URI)).build())
                        .callback(resp -> {
                            assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(200);
                            return null;
                        })
                        .ctx(ctx)
                        .build();

        execute(Arrays.asList(request, request), getHttpClientBuilderConsumer());

    }

    @Test
    public void requestStoreCookieWithClientContextTest() {
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
        ClientContext ctx = ClientContext.builder().requestConfig(rc).build();


        Request<?> request1 =
                Request
                        .builder()
                        .host(HOST)
                        .request(HttpRequestBuilder.GET(URI.create(RESOURCE_URI)).build())
                        .callback(resp -> {
                            assertThat(resp.getStatusLine().getStatusCode()).isEqualTo(200);
                            return null;
                        })
                        .ctx(ctx)
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
                        .ctx(ctx)
                        .build();


        execute(Arrays.asList(request1, request2), getHttpClientBuilderConsumer());
    }

    protected Consumer<HttpClientCommonBuilder<?>> getHttpClientBuilderConsumer() {
        return b -> {
        };
    }
}



