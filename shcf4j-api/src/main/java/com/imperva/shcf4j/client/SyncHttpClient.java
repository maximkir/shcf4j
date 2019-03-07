package com.imperva.shcf4j.client;

import com.imperva.shcf4j.HttpHost;
import com.imperva.shcf4j.HttpRequest;
import com.imperva.shcf4j.HttpResponse;
import com.imperva.shcf4j.ProcessingException;
import com.imperva.shcf4j.client.protocol.ClientContext;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Function;

/**
 * <b>SyncHttpClient</b>
 *
 * <p>
 * This interface represents only the most basic contract for HTTP request
 * execution. It imposes no restrictions or particular details on the request
 * execution process and leaves the specifics of state management,
 * authentication and redirect handling up to individual implementations.
 * </p>
 *
 * @author maxim.kirilov
 *
 */
public interface SyncHttpClient extends Closeable {


    /**
     * Executes HTTP request using the default context.
     *
     * @param target  the target host for the request.
     * @param request the request to execute
     * @return the response to the request. This is always a final response,
     *         never an intermediate response with an 1xx status code.
     *         Whether redirects or authentication challenges will be returned
     *         or handled automatically depends on the implementation and
     *         configuration of this client4.
     * @throws ProcessingException in case of a problem or the connection was aborted
     */
    HttpResponse execute(HttpHost target, HttpRequest request);

    /**
     * Executes HTTP request using the default context.
     *
     * @param target  the target host for the request.
     * @param request the request to execute
     * @param ctx     client4 execution context
     * @return the response to the request. This is always a final response,
     *         never an intermediate response with an 1xx status code.
     *         Whether redirects or authentication challenges will be returned
     *         or handled automatically depends on the implementation and
     *         configuration of this client4.
     * @throws ProcessingException in case of a problem or the connection was aborted
     */
    HttpResponse execute(HttpHost target, HttpRequest request, ClientContext ctx);


    /**
     * Executes HTTP request using the default context and processes the
     * response using the given response handler.
     *
     * Implementing classes are required to ensure that the content entity
     * associated with the response is fully consumed and the underlying
     * connection is released back to the connection manager automatically
     * in all cases relieving individual {@link Function}s from
     * having to manage resource de allocation internally.
     *
     * @param <T> the response outcome
     * @param target  the target host for the request.
     * @param request the request to execute
     * @param handler the response handler
     * @return the response object as generated by the response handler.
     * @throws ProcessingException in case of a problem or the connection was aborted
     */
    <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler);


    /**
     * Executes HTTP request using the default context and processes the
     * response using the given response handler.
     *
     * Implementing classes are required to ensure that the content entity
     * associated with the response is fully consumed and the underlying
     * connection is released back to the connection manager automatically
     * in all cases relieving individual {@link Function}s from
     * having to manage resource de allocation internally.
     *
     * @param <T> the response outcome
     * @param target  the target host for the request.
     * @param request the request to execute
     * @param handler the response handler
     * @param ctx     client4 execution context
     * @return the response object as generated by the response handler.
     * @throws ProcessingException in case of a problem or the connection was aborted
     */
    <T> T execute(HttpHost target, HttpRequest request, Function<HttpResponse, ? extends T> handler, ClientContext ctx);


    @Override
    default void close() throws IOException { }
}
