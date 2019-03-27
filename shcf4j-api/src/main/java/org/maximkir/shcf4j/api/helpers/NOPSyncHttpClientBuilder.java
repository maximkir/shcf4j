package org.maximkir.shcf4j.test.helpers;

import org.maximkir.shcf4j.test.MutableHttpRequest;
import org.maximkir.shcf4j.test.SyncHttpClientBuilder;
import org.maximkir.shcf4j.test.HttpHost;
import org.maximkir.shcf4j.test.client.CredentialsProvider;
import org.maximkir.shcf4j.test.client.SyncHttpClient;
import org.maximkir.shcf4j.test.client.config.RequestConfig;
import org.maximkir.shcf4j.test.config.SocketConfig;
import org.maximkir.shcf4j.test.conn.ssl.SSLSessionStrategy;

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
