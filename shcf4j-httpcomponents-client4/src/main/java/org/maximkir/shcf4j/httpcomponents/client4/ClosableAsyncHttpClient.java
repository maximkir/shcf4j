package org.maximkir.shcf4j.httpcomponents.client4;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * <b>InternalHttpAsyncClient</b>
 *
 * @author <font color="blue">Maxim Kirilov</font>
 */
class ClosableAsyncHttpClient implements AsyncHttpClient {

    private final CloseableHttpAsyncClient asyncClient;

    ClosableAsyncHttpClient(CloseableHttpAsyncClient asyncClient) {
        this.asyncClient = asyncClient;
        this.asyncClient.start();
    }


    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request) {
        return execute(target, request, null);
    }

    @Override
    public CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request, ClientContext ctx) {

        final CompletableFuture<HttpResponse> cf = new CompletableFuture<>();
        this.asyncClient.execute(
                ConversionUtils.convert(target),
                ConversionUtils.convert(request),
                ConversionUtils.convert(ctx),
                new FutureCallback<org.apache.http.HttpResponse>() {

                    @Override
                    public void cancelled() {
                        cf.cancel(false);
                    }

                    @Override
                    public void completed(org.apache.http.HttpResponse httpResponse) {
                        cf.complete(ConversionUtils.convert(httpResponse));
                    }

                    @Override
                    public void failed(Exception ex) {
                        cf.completeExceptionally(ex);
                    }
                });

        return cf;
    }

    @Override
    public void close() throws IOException {
        this.asyncClient.close();
    }
}
