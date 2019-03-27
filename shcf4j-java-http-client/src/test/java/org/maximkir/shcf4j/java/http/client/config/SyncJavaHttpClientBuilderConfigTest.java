package org.maximkir.shcf4j.api.java.http.client.config;

import org.maximkir.shcf4j.api.NotSupportedException;
import org.maximkir.shcf4j.api.SyncClientBaseTest;
import org.maximkir.shcf4j.api.config.HttpClientBuilderConfigTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SyncJavaHttpClientBuilderConfigTest extends HttpClientBuilderConfigTest implements SyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }


    @Override
    public void globalRequestTimeoutTest() {
        //Currently not supported by JDK11 HTTP client
        throw new NotSupportedException();
    }

    @Override
    public void requestInterceptorTest() {
        //Currently not supported by JDK11 HTTP client
    }

    @Override
    public void maxRedirectsExceededTest() {
        //Currently not supported by JDK11 HTTP client
        throw new NotSupportedException();
    }


    @Override
    public void basicAuthenticationSpecificScopeTest() {
    }


}
