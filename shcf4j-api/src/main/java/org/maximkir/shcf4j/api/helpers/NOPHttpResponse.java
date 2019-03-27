package org.maximkir.shcf4j.api.helpers;

import org.maximkir.shcf4j.api.Header;
import org.maximkir.shcf4j.api.HttpEntity;
import org.maximkir.shcf4j.api.HttpResponse;
import org.maximkir.shcf4j.api.StatusLine;

import java.util.Collections;
import java.util.List;

/**
 * <b>NOPHttpResponse</b>
 *
 *
 * <p>
 *     An empty {@link HttpResponse} that supposed to be returned from an {@link NOPSyncHttpClient}
 * </p>
 *
 * @author maxim.kirilov
 */
class NOPHttpResponse implements HttpResponse {


    static final HttpResponse INSTANCE = new NOPHttpResponse();


    @Override
    public StatusLine getStatusLine() {
        return StatusLine.builder().build();
    }

    @Override
    public HttpEntity getEntity() {
        return null;
    }


    @Override
    public boolean containsHeader(String name) {
        return false;
    }

    @Override
    public List<? extends Header> getHeaders(String name) {
        return Collections.emptyList();
    }

    @Override
    public List<? extends Header> getAllHeaders() {
        return Collections.emptyList();
    }

}
