package ee.estonia.paymentapi.controllers;

import ee.estonia.paymentapi.exceptions.CurrencyNotMatchException;
import ee.estonia.paymentapi.exceptions.DetailsMissingException;
import ee.estonia.paymentapi.exceptions.WrongTypeException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(WrongTypeException.class)
    public String handleWrongTypeException(WrongTypeException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(CurrencyNotMatchException.class)
    public String handleWrongTypeException(CurrencyNotMatchException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(DetailsMissingException.class)
    public String handleWrongTypeException(DetailsMissingException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleValidationException(HttpMessageNotReadableException ex){
        return "Amount should be digit";
    }
}
