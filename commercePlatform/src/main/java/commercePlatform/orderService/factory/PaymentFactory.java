package commercePlatform.orderService.factory;

import commercePlatform.orderService.domain.model.PaymentStrategy.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentFactory {

    private final Map<PaymentMethod, Payment> paymentsMethods;

    public PaymentFactory(CashPayment cashPayment, CreditCardPayment creditCardPayment, MercadoPagoPayment mpPayment) {
             paymentsMethods = Map.of(PaymentMethod.CASH, cashPayment,
                                      PaymentMethod.CREDIT_CARD, creditCardPayment,
                                      PaymentMethod.MERCADO_PAGO, mpPayment);
    }

    public Payment getPayment(PaymentMethod method){
        return paymentsMethods.get(method);
    }
}
