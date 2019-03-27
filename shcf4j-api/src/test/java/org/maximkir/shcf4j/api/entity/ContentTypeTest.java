package org.maximkir.shcf4j.api.entity;

import org.junit.Test;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.*;

public class ContentTypeTest {

    @Test
    public void createApplicationXMLTest() {
        ContentType contentType = ContentType.createApplicationXML();
        performActualComparison(contentType, "application/xml", ISO_8859_1);
    }

    @Test
    public void createApplicationJSONTest() {
        ContentType contentType = ContentType.createApplicationJSON();
        performActualComparison(contentType, "application/json", UTF_8);
    }

    @Test
    public void createTextPlainTest() {
        ContentType contentType = ContentType.createTextPlain();
        performActualComparison(contentType, "text/plain", ISO_8859_1);
    }

    @Test
    public void createTextXMLTest() {
        ContentType contentType = ContentType.createTextXML();
        performActualComparison(contentType, "text/xml", ISO_8859_1);
    }

    @Test
    public void createApplicationFormUrlEncodedTest() {
        ContentType contentType = ContentType.createApplicationFormUrlEncoded();
        performActualComparison(contentType, "application/x-www-form-urlencoded", ISO_8859_1);
    }

    @Test
    public void createApplicationOctetStreamTest() {
        ContentType contentType = ContentType.createApplicationOctetStream();
        performActualComparison(contentType, "application/octet-stream", null);
    }



    private void performActualComparison(ContentType contentType, String mimeType, Charset charset) {
        assertThat(contentType.getMimeType()).isEqualTo(mimeType);
        if (charset != null) {
            assertThat(contentType.getCharset()).isEqualByComparingTo(charset);
        } else {
            assertThat(contentType.getCharset()).isNull();
        }
    }
}