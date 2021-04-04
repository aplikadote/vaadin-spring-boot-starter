package vaadin.spring.boot.starter;

public class SampleApplicationException extends Exception {

    public SampleApplicationException(String message) {
        super(message);
    }

    public SampleApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
