package org.maximkir.shcf4j.api.helpers;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

class NOPAsyncHttpClient implements AsyncHttpClient {

    static final AsyncHttpClient INSTANCE = new NOPAsyncHttpClient();


    @Override
    public void close() throws IOException {

    }

    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request) {
        return CompletableFuture.completedFuture(NOPHttpResponse.INSTANCE);
    }

    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request, ClientContext ctx) {
        return CompletableFuture.completedFuture(NOPHttpResponse.INSTANCE);
    }
}
