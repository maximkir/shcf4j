package org.maximkir.shcf4j.test.helpers;

import org.maximkir.shcf4j.test.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.test.SyncHttpClientBuilder;
import org.maximkir.shcf4j.test.spi.SHC4JServiceProvider;

public class NOPServiceProvider implements SHC4JServiceProvider {

    public static final SHC4JServiceProvider INSTANCE = new NOPServiceProvider();


    @Override
    public SyncHttpClientBuilder getHttpClientBuilder() {
        return NOPSyncHttpClientBuilder.INSTANCE;
    }

    @Override
    public AsyncHttpClientBuilder getHttpAsyncClientBuilder() {
        return NOPAsyncHttpClientBuilder.INSTANCE;
    }

}
