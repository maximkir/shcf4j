package org.maximkir.shcf4j.api.java.http.client.request.multipart;

import org.maximkir.shcf4j.api.AsyncClientBaseTest;
import org.maximkir.shcf4j.api.request.multipart.MultipartRequestsTest;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class AsyncJavaHttpClientMultipartRequestsTest extends MultipartRequestsTest implements AsyncClientBaseTest {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
