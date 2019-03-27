package org.maximkir.shcf4j.api.client;

import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequest;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.client.protocol.ClientContext;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * <b>AsyncHttpClient</b>
 *
 * <p>
 * This interface represents only the most basic contract for HTTP request execution.
 * It imposes no restrictions or particular details on the request execution process and leaves the specifics
 * of state management, authentication and redirect handling up to individual implementations.
 * </p>
 *
 * @author maxim.kirilov
 */
public interface AsyncHttpClient extends Closeable {


    /**
     * Initiates asynchronous HTTP request execution using the given context.
     *
     * @param target   the target host for the request.
     * @param request  the request to execute
     * @return {@link CompletableFuture} representing pending completion of the operation.
     */
    CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request);

    /**
     * Initiates asynchronous HTTP request execution using the given context.
     *
     * @param target   the target host for the request.
     * @param request  the request to execute
     * @param ctx      client4 execution context
     * @return {@link CompletableFuture} representing pending completion of the operation.
     */
    CompletableFuture<HttpResponse> execute(HttpHost target, HttpRequest request, ClientContext ctx);

    @Override
    default void close() throws IOException { }

}
