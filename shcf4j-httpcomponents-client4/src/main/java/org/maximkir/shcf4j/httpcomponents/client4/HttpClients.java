package org.maximkir.shcf4j.api.httpcomponents.client4;

import org.maximkir.shcf4j.api.client.SyncHttpClient;

/**
 * <b>HttpClients</b>
 * <p>
 * Factory methods for {@link SyncHttpClient} instances.
 * </p>
 *
 * @author maxim.kirilov
 */
public class HttpClients {


    /**
     * Creates builder object for construction of custom
     * {@link SyncHttpClient} instances.
     *
     * @return {@code SyncHttpClientBuilder}
     */
    public static HttpComponentsSyncHttpClientBuilder custom() {
        return new HttpComponentsSyncHttpClientBuilder(
                org.apache.http.impl.client.HttpClients.custom()
        );
    }

}
