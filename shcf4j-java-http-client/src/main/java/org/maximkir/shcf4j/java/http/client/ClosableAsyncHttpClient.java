package org.maximkir.shcf4j.api.java.http.client;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;

import java.io.InputStream;
import java.net.http.HttpClient;
import java.util.concurrent.CompletableFuture;

public class ClosableAsyncHttpClient implements AsyncHttpClient {


    private final HttpClient httpClient;

    ClosableAsyncHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request) {
        return execute(target, request, null);
    }

    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request, ClientContext ctx) {

        java.net.http.HttpRequest httpRequest = JavaHttpRequestFactory.createJavaHttpRequest(target, request, ctx);
        CompletableFuture<java.net.http.HttpResponse<InputStream>> cf =
                this.httpClient.sendAsync(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofInputStream());

        return cf.thenApplyAsync(HttpResponseImpl::new);
    }

}
