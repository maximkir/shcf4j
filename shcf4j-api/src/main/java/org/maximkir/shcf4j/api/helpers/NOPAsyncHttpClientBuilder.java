package org.maximkir.shcf4j.test.helpers;

import org.maximkir.shcf4j.test.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.test.HttpHost;
import org.maximkir.shcf4j.test.MutableHttpRequest;
import org.maximkir.shcf4j.test.client.AsyncHttpClient;
import org.maximkir.shcf4j.test.client.CredentialsProvider;
import org.maximkir.shcf4j.test.client.config.RequestConfig;
import org.maximkir.shcf4j.test.conn.ssl.SSLSessionStrategy;
import org.maximkir.shcf4j.test.nio.reactor.IOReactorConfig;

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
