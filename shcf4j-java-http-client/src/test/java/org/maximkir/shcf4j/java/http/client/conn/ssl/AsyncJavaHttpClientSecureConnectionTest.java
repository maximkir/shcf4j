package org.maximkir.shcf4j.java.http.client.conn.ssl;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.conn.ssl.SecureConnectionTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AsyncJavaHttpClientSecureConnectionTest extends SecureConnectionTest implements AsyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }


}
