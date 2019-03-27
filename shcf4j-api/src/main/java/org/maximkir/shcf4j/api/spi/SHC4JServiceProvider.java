package org.maximkir.shcf4j.test.spi;

import org.maximkir.shcf4j.test.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.test.SyncHttpClientBuilder;


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
