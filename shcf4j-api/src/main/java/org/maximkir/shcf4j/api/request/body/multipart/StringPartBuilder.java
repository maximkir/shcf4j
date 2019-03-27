package org.maximkir.shcf4j.test.request.body.multipart;

import org.maximkir.shcf4j.test.entity.ContentType;

import java.util.Objects;

/**
 * <b>StringPartBuilder</b>
 *
 * @author maxim.kirilov
 */
public class StringPartBuilder extends PartBuilder<StringPartBuilder> {

    protected String value;

    StringPartBuilder() {
        contentType(ContentType.createTextPlain()); // Default, in order to preserve behavior between different providers (which can use different defaults)
    }

    @Override
    public Part build() {
        Objects.requireNonNull(value, "value");
        return new StringPart(this);
    }


    public StringPartBuilder value(String value) {
        this.value = value;
        return this;
    }
}
