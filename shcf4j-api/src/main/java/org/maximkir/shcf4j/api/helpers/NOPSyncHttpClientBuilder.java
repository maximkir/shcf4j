package org.maximkir.shcf4j.api.helpers;

import org.maximkir.shcf4j.api.MutableHttpRequest;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;
import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.config.SocketConfig;
import org.maximkir.shcf4j.api.conn.ssl.SSLSessionStrategy;

import java.util.function.Consumer;


/**
 * <b>NOPSyncHttpClientBuilder</b>
 *
 * <p>
 * A trivial implementation of {@link SyncHttpClientBuilder}
 * </p>
 *
 * @author maxim.kirilov
 */
class NOPSyncHttpClientBuilder implements SyncHttpClientBuilder {

    static final SyncHttpClientBuilder INSTANCE = new NOPSyncHttpClientBuilder();

    @Override
    public SyncHttpClientBuilder setSSLSessionStrategy(SSLSessionStrategy strategy) {
        return this;
    }

    @Override
    public SyncHttpClientBuilder setDefaultSocketConfig(SocketConfig socketConfig) {
        return this;
    }

    @Override
    public SyncHttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        return this;
    }

    @Override
    public SyncHttpClientBuilder setProxy(HttpHost proxy) {
        return this;
    }

    @Override
    public SyncHttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider cp) {
        return this;
    }

    @Override
    public SyncHttpClientBuilder addRequestInterceptor(Consumer<MutableHttpRequest> request) {
        return this;
    }


    @Override
    public SyncHttpClient build() {
        return NOPSyncHttpClient.INSTANCE;
    }
}
