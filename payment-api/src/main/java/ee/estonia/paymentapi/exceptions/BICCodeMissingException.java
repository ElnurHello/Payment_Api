package ee.estonia.paymentapi.exceptions;

public class BICCodeMissingException extends RuntimeException {
    public BICCodeMissingException(String value) {
        super(value);
    }
}
