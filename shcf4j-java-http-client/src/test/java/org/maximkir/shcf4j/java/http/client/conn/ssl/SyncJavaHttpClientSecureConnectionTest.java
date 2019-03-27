package org.maximkir.shcf4j.java.http.client.conn.ssl;

import org.maximkir.shcf4j.api.SyncClientBaseTest;
import org.maximkir.shcf4j.api.conn.ssl.SecureConnectionTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SyncJavaHttpClientSecureConnectionTest extends SecureConnectionTest implements SyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }



}
