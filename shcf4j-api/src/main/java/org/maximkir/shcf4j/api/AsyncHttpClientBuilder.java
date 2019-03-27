package org.maximkir.shcf4j.api;

import org.maximkir.shcf4j.api.client.AsyncHttpClient;
import org.maximkir.shcf4j.api.nio.reactor.IOReactorConfig;

import java.util.concurrent.ThreadFactory;

public interface AsyncHttpClientBuilder extends HttpClientCommonBuilder<AsyncHttpClientBuilder> {


    AsyncHttpClientBuilder setThreadFactory(final ThreadFactory threadFactory);


    AsyncHttpClientBuilder setDefaultSocketConfig(IOReactorConfig ioReactorConfig);


    AsyncHttpClient build();

}
