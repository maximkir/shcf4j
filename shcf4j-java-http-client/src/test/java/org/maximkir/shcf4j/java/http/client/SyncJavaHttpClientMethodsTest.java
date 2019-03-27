package org.maximkir.shcf4j.api.java.http.client;

import org.maximkir.shcf4j.api.SyncClientBaseTest;
import org.maximkir.shcf4j.api.request.HttpMethodsTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SyncJavaHttpClientMethodsTest extends HttpMethodsTest implements SyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Override
    public void basicAuthenticationTest()  {
        //A specific authenticator cannot be customized per request just for the entire client
    }


}
