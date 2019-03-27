package org.maximkir.shcf4j.api.java.http.client;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.request.HttpMethodsTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AsyncJavaHttpClientMethodsTest extends HttpMethodsTest implements AsyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Override
    public void basicAuthenticationTest() {
        //A specific authenticator cannot be customized per request just for the entire client
    }


}
