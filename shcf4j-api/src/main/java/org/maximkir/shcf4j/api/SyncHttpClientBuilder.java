package org.maximkir.shcf4j.api;

import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.maximkir.shcf4j.api.config.SocketConfig;

/**
 * <b>SyncHttpClientBuilder</b>
 *
 * @author maxim.kirilov
 */
public interface SyncHttpClientBuilder extends HttpClientCommonBuilder<SyncHttpClientBuilder> {


    SyncHttpClientBuilder setDefaultSocketConfig(SocketConfig socketConfig);

    SyncHttpClient build();

}
