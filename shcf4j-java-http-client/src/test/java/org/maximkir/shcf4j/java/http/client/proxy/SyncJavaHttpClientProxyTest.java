package org.maximkir.shcf4j.api.java.http.client.proxy;

import org.maximkir.shcf4j.api.SyncClientBaseTest;
import org.maximkir.shcf4j.api.proxy.ProxyTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SyncJavaHttpClientProxyTest extends ProxyTest implements SyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
