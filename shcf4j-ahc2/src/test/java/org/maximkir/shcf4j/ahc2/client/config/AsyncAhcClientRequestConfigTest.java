package org.maximkir.shcf4j.ahc2.client.config;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.HttpRequestBuilder;
import org.maximkir.shcf4j.api.Request;
import org.maximkir.shcf4j.api.client.config.CookieSpecs;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;
import org.maximkir.shcf4j.api.config.RequestConfigTest;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.absent;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public class AsyncAhcClientRequestConfigTest extends RequestConfigTest implements AsyncClientBaseTest {

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

        execute(Arrays.asList(request, request), builder -> builder.setDefaultRequestConfig(rc));

    }

}
