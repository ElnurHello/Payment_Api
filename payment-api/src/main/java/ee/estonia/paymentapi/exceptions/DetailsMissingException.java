package ee.estonia.paymentapi.exceptions;

public class DetailsMissingException extends RuntimeException {
    public DetailsMissingException(String s) {
        super(s);
    }
}
