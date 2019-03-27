package org.maximkir.shcf4j.api;


import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProtocolVersion {

    private final String protocol;
    private final int major;
    private final int minor;

}
