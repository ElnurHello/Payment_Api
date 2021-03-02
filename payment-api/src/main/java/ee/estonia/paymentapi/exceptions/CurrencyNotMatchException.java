package ee.estonia.paymentapi.exceptions;

public class CurrencyNotMatchException extends RuntimeException {
    public CurrencyNotMatchException(String value) {
        super(value);
    }
}
