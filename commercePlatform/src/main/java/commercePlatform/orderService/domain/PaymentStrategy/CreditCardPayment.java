package commercePlatform.orderService.domain.PaymentStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditCardPayment implements Payment {

    @Override
    public BigDecimal payWithADiscountApplied(BigDecimal total) {
        System.out.print("The Payment was made with CREDIT CARD \n");
        return total; // Sin descuento pagando con tarjeta de crédito
    }

}
