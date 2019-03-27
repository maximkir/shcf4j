package org.maximkir.shcf4j.java.http.client;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.MutableHttpRequest;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;
import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.config.SocketConfig;
import org.maximkir.shcf4j.api.conn.ssl.SSLSessionStrategy;

import javax.net.ssl.SSLException;
import java.net.http.HttpClient;
import java.util.function.Consumer;

class JavaSyncHttpClientBuilder implements SyncHttpClientBuilder {

    private final HttpClient.Builder builder = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1);

    @Override
    public SyncHttpClientBuilder setDefaultSocketConfig(SocketConfig socketConfig) {
        return this;
    }

    @Override
    public SyncHttpClient build() {
        return new ClosableSyncHttpClient(builder.build());
    }

    @Override
    public SyncHttpClientBuilder setSSLSessionStrategy(SSLSessionStrategy strategy) throws SSLException {
        HttpClientBuilderUtils.setSSLSessionStrategy(strategy, this.builder);
        return this;
    }

    @Override
    public SyncHttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        HttpClientBuilderUtils.setDefaultRequestConfig(config, this.builder);
        return this;
    }

    @Override
    public SyncHttpClientBuilder setProxy(HttpHost proxy) {
        HttpClientBuilderUtils.setProxy(proxy, this.builder);
        return this;
    }

    @Override
    public SyncHttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider cp) {
        HttpClientBuilderUtils.setDefaultCredentialsProvider(cp, this.builder);
        return this;
    }

    @Override
    public SyncHttpClientBuilder addRequestInterceptor(Consumer<MutableHttpRequest> interceptor) {
        return this;
    }
}
