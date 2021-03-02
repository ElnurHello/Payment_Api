package ee.estonia.paymentapi.services;

import ee.estonia.paymentapi.entity.Payment;
import ee.estonia.paymentapi.exceptions.BICCodeMissingException;
import ee.estonia.paymentapi.exceptions.CurrencyNotMatchException;
import ee.estonia.paymentapi.exceptions.DetailsMissingException;
import ee.estonia.paymentapi.exceptions.WrongTypeException;
import ee.estonia.paymentapi.repos.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(Payment payment) {
        String type = payment.getType();
        switch (type) {
            case "TYPE1":
                return type1Action(payment);
            case "TYPE2":
                return type2Action(payment);
            case "TYPE3":
                return type3Action(payment);
            default:
                throw new WrongTypeException("Type is not correct");
        }
    }

    private Payment type3Action(Payment payment) {
        if (!(payment.getCurrency().equalsIgnoreCase("euro") || (payment.getCurrency().equalsIgnoreCase("usd"))))
            throw new CurrencyNotMatchException("Currency should be either USD or EURO");
        else if (payment.getBICCode() == null || payment.getBICCode().isEmpty())
            throw new BICCodeMissingException("BIC Code should not be empty");
        return paymentRepository.save(payment);

    }

    private Payment type2Action(Payment payment) {
        if (!payment.getCurrency().equalsIgnoreCase("usd"))
            throw new CurrencyNotMatchException("Currency should be in USD");
        return paymentRepository.save(payment);
    }

    private Payment type1Action(Payment payment) {
        if (!payment.getCurrency().equalsIgnoreCase("euro"))
            throw new CurrencyNotMatchException("Currency should be in EURO");
        else if (payment.getDetails()==null || payment.getDetails().isEmpty())
            throw new DetailsMissingException("Details shouldn't be empty");
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByType(String type) {
        return paymentRepository.findPaymentByTypeEquals(type);
    }
}
