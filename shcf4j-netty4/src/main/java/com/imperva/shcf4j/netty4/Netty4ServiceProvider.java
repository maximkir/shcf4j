package com.imperva.shcf4j.netty4;

import com.imperva.shcf4j.AsyncHttpClientBuilder;
import com.imperva.shcf4j.SyncHttpClientBuilder;
import com.imperva.shcf4j.netty4.client.async.AsyncNettyHttpClientBuilder;
import com.imperva.shcf4j.spi.SHC4JServiceProvider;


/**
 * @author maxim.kirilov
 */
public class Netty4ServiceProvider implements SHC4JServiceProvider {


    @Override
    public SyncHttpClientBuilder getHttpClientBuilder() {
        return null;
    }

    @Override
    public AsyncHttpClientBuilder getHttpAsyncClientBuilder() {
        return new AsyncNettyHttpClientBuilder();
    }
}
