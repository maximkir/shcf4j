package org.maximkir.shcf4j.api.helpers;

import org.maximkir.shcf4j.api.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.MutableHttpRequest;
import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.conn.ssl.SSLSessionStrategy;
import org.maximkir.shcf4j.api.nio.reactor.IOReactorConfig;

import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;

class NOPAsyncHttpClientBuilder implements AsyncHttpClientBuilder {

    static final AsyncHttpClientBuilder INSTANCE = new NOPAsyncHttpClientBuilder();

    @Override
    public AsyncHttpClientBuilder setThreadFactory(ThreadFactory threadFactory) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder setDefaultSocketConfig(IOReactorConfig ioReactorConfig) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder setSSLSessionStrategy(SSLSessionStrategy strategy) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder setDefaultRequestConfig(RequestConfig config) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder setProxy(HttpHost proxy) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder setDefaultCredentialsProvider(CredentialsProvider cp) {
        return this;
    }

    @Override
    public AsyncHttpClientBuilder addRequestInterceptor(Consumer<MutableHttpRequest> request) {
        return this;
    }


    @Override
    public AsyncHttpClient build() {
        return NOPAsyncHttpClient.INSTANCE;
    }
}
