package org.maximkir.shcf4j.example.programmatically;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.maximkir.shcf4j.api.HttpClientBuilderFactory;
import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequestBuilder;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.junit.Rule;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.concurrent.CompletableFuture;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class Programmatically {

    private static final int WIREMOCK_PORT = 8089;

    @Rule
    public WireMockClassRule instanceRule = new WireMockClassRule(WIREMOCK_PORT);

    @Test
    public void programmaticallyTest() throws Exception {

        instanceRule
                .stubFor(get(urlEqualTo("/"))
                        .willReturn(aResponse().withStatus(HttpURLConnection.HTTP_OK)));


        SyncHttpClient syncHttpClient =
                HttpClientBuilderFactory
                        .getHttpClientBuilder()
                        .build();

        syncHttpClient.execute(
                HttpHost.builder().schemeName("http").hostname("localhost").port(WIREMOCK_PORT).build(),
                HttpRequestBuilder.GET().uri("/").build(),
                response -> {
                    System.out.println(response.getStatusLine());
                    return response.getStatusLine().getStatusCode() == 200;
                });
        syncHttpClient.close();


        AsyncHttpClient asyncHttpClient =
                HttpClientBuilderFactory
                        .getHttpAsyncClientBuilder()
                        .build();

        CompletableFuture<HttpResponse> completableFuture =
                asyncHttpClient.execute(
                        HttpHost.builder().schemeName("http").hostname("localhost").port(WIREMOCK_PORT).build(),
                        HttpRequestBuilder.GET().uri("/").build()
                );

        completableFuture.thenApply(response -> {
            System.out.println(response.getStatusLine());
            return response.getStatusLine().getStatusCode() == 200;
        }).get();

        asyncHttpClient.close();

    }
}
