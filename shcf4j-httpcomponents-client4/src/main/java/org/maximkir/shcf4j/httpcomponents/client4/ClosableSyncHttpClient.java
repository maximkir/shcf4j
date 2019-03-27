package org.maximkir.shcf4j.api.httpcomponents.client4;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.ProcessingException;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;

import java.io.IOException;
import java.util.function.Function;

/**
 * <b>SyncHttpClientBase</b>
 * <p>
 * Base implementation of {@link SyncHttpClient}
 * </p>
 *
 * @author maxim.kirilov
 */
public class ClosableSyncHttpClient implements SyncHttpClient {

    private final org.apache.http.impl.client.CloseableHttpClient httpClient;

    protected ClosableSyncHttpClient(org.apache.http.impl.client.CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public void close() throws IOException {
        httpClient.close();
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
        try {
            return httpClient.execute(
                    ConversionUtils.convert(target),
                    ConversionUtils.convert(request),
                    response -> handler.apply(ConversionUtils.convert(response)),
                    ConversionUtils.convert(ctx)
            );
        } catch (IOException ioException) {
            throw new ProcessingException(ioException);
        }
    }
}
