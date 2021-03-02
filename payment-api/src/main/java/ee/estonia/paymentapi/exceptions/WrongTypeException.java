package ee.estonia.paymentapi.exceptions;

public class WrongTypeException extends RuntimeException {
    public WrongTypeException(String value) {
        super(value);
    }
}
