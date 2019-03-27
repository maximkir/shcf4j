package org.maximkir.shcf4j.test.helpers;

import org.maximkir.shcf4j.test.HttpHost;
import org.maximkir.shcf4j.test.HttpRequest;
import org.maximkir.shcf4j.test.HttpResponse;
import org.maximkir.shcf4j.test.client.AsyncHttpClient;
import org.maximkir.shcf4j.test.client.protocol.ClientContext;

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
