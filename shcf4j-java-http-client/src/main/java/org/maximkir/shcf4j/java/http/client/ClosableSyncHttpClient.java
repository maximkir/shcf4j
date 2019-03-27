package org.maximkir.shcf4j.api.java.http.client;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.ProcessingException;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.util.function.Function;

class ClosableSyncHttpClient implements SyncHttpClient {

    private final HttpClient httpClient;

    ClosableSyncHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) {
        return execute(target, request, (ClientContext) null);
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, ClientContext ctx) {
        return execute(
                target,
                request,
                Function.identity(),
                ctx
        );
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler) {
        return execute(target, request, handler, null);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler, ClientContext ctx) {

        java.net.http.HttpRequest httpRequest = JavaHttpRequestFactory.createJavaHttpRequest(target, request, ctx);

        try {
            java.net.http.HttpResponse<InputStream> response =
                    this.httpClient.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofInputStream());

            return handler.apply(new HttpResponseImpl(response));

        } catch (IOException | InterruptedException e){
            throw new ProcessingException(e);
        }

    }


}
