package org.maximkir.shcf4j.test;

import org.maximkir.shcf4j.test.client.AsyncHttpClient;
import org.maximkir.shcf4j.test.nio.reactor.IOReactorConfig;

import java.util.concurrent.ThreadFactory;

public interface AsyncHttpClientBuilder extends HttpClientCommonBuilder<AsyncHttpClientBuilder> {


    AsyncHttpClientBuilder setThreadFactory(final ThreadFactory threadFactory);


    AsyncHttpClientBuilder setDefaultSocketConfig(IOReactorConfig ioReactorConfig);


    AsyncHttpClient build();

}
