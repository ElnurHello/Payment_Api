package ee.estonia.paymentapi.controllers;

import ee.estonia.paymentapi.entity.Payment;
import ee.estonia.paymentapi.services.PaymentService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {

        return paymentService.getAllPayments();
    }

    @PostMapping("/create")
    public Payment createPayment(@Valid @RequestBody Payment payment, BindingResult bindingResult) {
        StringBuilder errors= new StringBuilder();
        if(bindingResult.hasErrors()){
            for(ObjectError err: bindingResult.getAllErrors()){
                errors.append(err.getDefaultMessage()).append("\n");
            }
            throw new ValidationException(errors.toString());
        }
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{type}")
    public List<Payment> getType1Payments(@PathVariable String type){
        return paymentService.getPaymentsByType(type.toUpperCase());
    }


}
