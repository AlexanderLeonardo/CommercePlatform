package commercePlatform.orderService.domain.PaymentStrategy;

import commercePlatform.orderService.interfaces.Payment;

import java.math.BigDecimal;

public class CreditCardPayment implements Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total; // Sin descuento pagando con tarjeta de crédito
    }
}
