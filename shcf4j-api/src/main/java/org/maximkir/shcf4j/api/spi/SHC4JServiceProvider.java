package org.maximkir.shcf4j.api.spi;

import org.maximkir.shcf4j.api.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;


/**
 * <b>SHC4JServiceProvider</b>
 *
 * <p>
 * An interface for HTTP client libraries vendors integrations.
 * </p>
 *
 * @author maxim.kirilov
 */
public interface SHC4JServiceProvider {

    SyncHttpClientBuilder getHttpClientBuilder();

    AsyncHttpClientBuilder getHttpAsyncClientBuilder();

    default void initialize() {
    }
}
