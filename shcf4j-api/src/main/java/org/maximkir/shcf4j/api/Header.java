package org.maximkir.shcf4j.test;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class Header {

    @NonNull
    private final String name;
    private final String value;

}
