package org.maximkir.shcf4j.api;

import org.maximkir.shcf4j.api.client.protocol.ClientContext;
import lombok.Builder;
import lombok.Value;

import java.util.function.Function;

@Builder
@Value
public class Request<T> {

    private final HttpHost host;

    private final HttpRequest request;

    private final Function<HttpResponse, T> callback;

    private final ClientContext ctx;
}
