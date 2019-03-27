package org.maximkir.shcf4j.test;


/**
 * <b>ProcessingException</b>
 *
 * <p>
 *  The exception of this type is thrown during HTTP request or response processing,
 *  to signal a runtime processing failure
 * </p>
 *
 * @author maxim.kirilov
 */
public class ProcessingException extends RuntimeException {

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(Throwable cause) {
        super(cause);
    }
}
