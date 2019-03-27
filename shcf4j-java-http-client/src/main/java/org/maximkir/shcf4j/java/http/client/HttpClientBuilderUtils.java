package org.maximkir.shcf4j.java.http.client;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.client.CredentialsProvider;
import org.maximkir.shcf4j.api.client.config.RequestConfig;
import org.maximkir.shcf4j.api.conn.ssl.SSLSessionStrategy;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Objects;

class HttpClientBuilderUtils {

    static void setSSLSessionStrategy(SSLSessionStrategy strategy, HttpClient.Builder builder)  throws SSLException {
        builder.sslParameters(new SSLParameters(strategy.getSupportedCipherSuites(), strategy.getSupportedProtocols()));

        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            KeyManager[] km = strategy.getKeyManagerFactory() != null ? strategy.getKeyManagerFactory().getKeyManagers() : null;
            TrustManager[] tm = strategy.getTrustManagerFactory() != null ? strategy.getTrustManagerFactory().getTrustManagers() : null;
            sslContext.init(km, tm, null);
            builder.sslContext(sslContext);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new SSLException(e);
        }
    }

    static void setDefaultRequestConfig(RequestConfig config, HttpClient.Builder builder){
        builder.followRedirects(config.isRedirectsEnabled() ? HttpClient.Redirect.ALWAYS : HttpClient.Redirect.NEVER);

        if (config.getProxy() != null) { // Handle Proxy
            setProxy(config.getProxy(), builder);
        }

        if (config.getConnectTimeoutMilliseconds() > 0) {
            builder.connectTimeout(Duration.ofMillis(config.getConnectTimeoutMilliseconds()));
        }

        switch (config.getCookieSpec()) {
            case IGNORE_COOKIES:
                builder.cookieHandler(new CookieManager(IgnoreAllCookieStore.INSTANCE, null));
                break;
            case STANDARD_RFC_6265:
                builder.cookieHandler(new CookieManager());
                break;
            default:
                throw new RuntimeException("Not supported Cookie spec: " + config.getCookieSpec());
        }
    }


    static void setProxy(HttpHost proxy, HttpClient.Builder builder){
        builder.proxy(ProxySelector.of(new InetSocketAddress(proxy.getHostname(), proxy.getPort())));
    }

    static void setDefaultCredentialsProvider(CredentialsProvider cp, HttpClient.Builder builder){
        Objects.requireNonNull(cp, "cp");
        var cred = cp.getCredentials();
        if (cred.size() != 1) {
            throw new RuntimeException("JDK11 HTTP Client builder supports only single credentials entry");
        }
        // JDK11 HTTP client supports only BASIC authentication scheme
        for (var e : cred.entrySet()) {
            var auth = CredentialsProviderAuthenticator.createAuthenticator(e.getKey(), e.getValue());
            builder.authenticator(auth);
        }
    }
}
