package org.maximkir.shcf4j.test.conn.ssl;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * <b>SSLSessionStrategy</b>
 *
 * <p>
 * TLS transport level security strategy.
 * </p>
 *
 * @author Maxim.Kirilov
 */
public interface SSLSessionStrategy {

    TrustManagerFactory getTrustManagerFactory();

    KeyManagerFactory getKeyManagerFactory();

    String[] getSupportedProtocols();

    String[] getSupportedCipherSuites();

    HostnameVerifier getHostnameVerifier();

}
