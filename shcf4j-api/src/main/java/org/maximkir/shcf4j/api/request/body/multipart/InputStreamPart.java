package org.maximkir.shcf4j.api.request.body.multipart;

import lombok.Getter;

import java.io.InputStream;

@Getter
public class InputStreamPart extends PartBase {

    private final InputStream inputStream;
    private final long contentLength;

    InputStreamPart(InputStreamPartBuilder builder) {
        super(builder);
        this.inputStream = builder.inputStream;
        this.contentLength = builder.contentLength;
    }

    @Override
    public String getTransferEncoding() {
        return super.getTransferEncoding() != null ? super.getTransferEncoding() : MIME.ENC_BINARY;
    }
}
