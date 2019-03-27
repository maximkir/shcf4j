package org.maximkir.shcf4j.java.http.client;

import org.maximkir.shcf4j.api.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;
import org.maximkir.shcf4j.api.spi.SHC4JServiceProvider;

public class JavaHttpClientServiceProvider implements SHC4JServiceProvider {


    @Override
    public SyncHttpClientBuilder getHttpClientBuilder() {
        return new JavaSyncHttpClientBuilder();
    }

    @Override
    public AsyncHttpClientBuilder getHttpAsyncClientBuilder() {
        return new JavaAsyncHttpClientBuilder();
    }
}
