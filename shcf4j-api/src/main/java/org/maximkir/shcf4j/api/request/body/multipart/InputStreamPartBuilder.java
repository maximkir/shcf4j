package org.maximkir.shcf4j.test.request.body.multipart;

import org.maximkir.shcf4j.test.entity.ContentType;

import java.io.InputStream;
import java.util.Objects;

public class InputStreamPartBuilder extends PartBuilder<InputStreamPartBuilder> {


    protected InputStream inputStream;
    protected long contentLength = -1;

    InputStreamPartBuilder() {
        contentType(ContentType.createApplicationOctetStream());
    }

    public InputStreamPartBuilder inputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public InputStreamPartBuilder contentLength(int contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    @Override
    public Part build() {
        Objects.requireNonNull(inputStream, "inputStream");
        return new InputStreamPart(this);
    }
}
