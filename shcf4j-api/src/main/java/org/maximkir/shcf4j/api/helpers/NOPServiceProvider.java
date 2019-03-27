package org.maximkir.shcf4j.api.helpers;

import org.maximkir.shcf4j.api.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;
import org.maximkir.shcf4j.api.spi.SHC4JServiceProvider;

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
