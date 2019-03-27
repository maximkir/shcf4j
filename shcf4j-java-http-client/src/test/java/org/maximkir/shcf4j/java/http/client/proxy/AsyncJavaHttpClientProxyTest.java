package org.maximkir.shcf4j.java.http.client.proxy;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.proxy.ProxyTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AsyncJavaHttpClientProxyTest extends ProxyTest implements AsyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
