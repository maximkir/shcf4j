package org.maximkir.shcf4j.test.conn.ssl;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * <b>AllowAllHostnameVerifier</b>
 *
 * <p>
 * The ALLOW_ALL HostnameVerifier essentially turns hostname verification off.
 * This implementation is a no-op, and never throws the SSLException.
 * </p>
 *
 * @author maxim.kirilov
 */
public class AllowAllHostnameVerifier implements HostnameVerifier {

    public static final HostnameVerifier INSTANCE = new AllowAllHostnameVerifier();


    private AllowAllHostnameVerifier() {
    }

    @Override
    public boolean verify(String s, SSLSession sslSession) {
        return true;
    }
}
