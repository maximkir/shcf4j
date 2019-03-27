package org.maximkir.shcf4j.api.request.body.multipart;

import org.maximkir.shcf4j.api.entity.ContentType;

import java.nio.file.Path;
import java.util.Objects;

public class FilePartBuilder extends PartBuilder<FilePartBuilder> {

    protected Path filePath;


    FilePartBuilder() {
    }

    public FilePartBuilder filePath(Path filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public Part build() {
        Objects.requireNonNull(filePath, "filePath");
        if (this.contentType == null){
            contentType(ContentType.probeContentType(filePath));
        }
        return new FilePart(this);
    }
}
