package commercePlatform.orderService.domain.PaymentStrategy;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CreditCardPayment extends Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total; // Sin descuento pagando con tarjeta de crédito
    }
}
