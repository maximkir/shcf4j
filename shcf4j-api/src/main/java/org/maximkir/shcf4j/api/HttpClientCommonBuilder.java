package org.maximkir.shcf4j.api;

import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.conn.ssl.SSLSessionStrategy;

import javax.net.ssl.SSLException;
import java.util.function.Consumer;

/**
 * <b>HttpClientCommonBuilder</b>
 *
 * <p>
 *     A common methods for both {@link SyncHttpClientBuilder} and {@link AsyncHttpClientBuilder}
 * </p>
 *
 * @author maxim.kirilov
 */
public interface HttpClientCommonBuilder<T> {

    T setSSLSessionStrategy(final SSLSessionStrategy strategy) throws SSLException;

    T setDefaultRequestConfig(final RequestConfig config);

    T setProxy(HttpHost proxy);

    T setDefaultCredentialsProvider(CredentialsProvider cp);

    T addRequestInterceptor(Consumer<MutableHttpRequest> interceptor);

}
