package org.maximkir.shcf4j.java.http.client.request.multipart;

import org.maximkir.shcf4j.api.SyncClientBaseTest;
import org.maximkir.shcf4j.api.request.multipart.MultipartRequestsTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SyncJavaHttpClientMultipartRequestsTest extends MultipartRequestsTest implements SyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
