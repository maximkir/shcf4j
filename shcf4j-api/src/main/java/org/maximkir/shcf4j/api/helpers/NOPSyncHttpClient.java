package org.maximkir.shcf4j.test.helpers;

import org.maximkir.shcf4j.test.HttpHost;
import org.maximkir.shcf4j.test.HttpRequest;
import org.maximkir.shcf4j.test.HttpResponse;
import org.maximkir.shcf4j.test.client.SyncHttpClient;
import org.maximkir.shcf4j.test.client.protocol.ClientContext;
import org.maximkir.shcf4j.test.spi.SHC4JServiceProvider;

import java.util.function.Function;


/**
 * <b>NOPSyncHttpClient</b>
 *
 * <p>
 * An implementation that used as fallback for non found {@link SHC4JServiceProvider}
 * </p>
 *
 * @author maxim.kirilov
 */
class NOPSyncHttpClient implements SyncHttpClient {


    static final SyncHttpClient INSTANCE = new NOPSyncHttpClient();


    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request) {
        return NOPHttpResponse.INSTANCE;
    }

    @Override
    public HttpResponse execute(HttpHost target, HttpRequest request, ClientContext ctx) {
        return NOPHttpResponse.INSTANCE;
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler) {
        return handler.apply(NOPHttpResponse.INSTANCE);
    }

    @Override
    public <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler, ClientContext ctx) {
        return handler.apply(NOPHttpResponse.INSTANCE);
    }

}
