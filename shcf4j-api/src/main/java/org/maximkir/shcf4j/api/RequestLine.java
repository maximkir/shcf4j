package org.maximkir.shcf4j.api;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RequestLine {

    private final String method;
    private final ProtocolVersion protocolVersion;
    private final String uri;

}
