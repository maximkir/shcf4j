package org.maximkir.shcf4j.java.http.client.config;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.config.RequestConfigTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AsyncJavaHttpClientRequestConfigTest extends RequestConfigTest implements AsyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }


    @Override
    public void requestStoreCookieWithClientContextTest() {
        //Currently not supported by JDK11 HTTP client
    }
}
