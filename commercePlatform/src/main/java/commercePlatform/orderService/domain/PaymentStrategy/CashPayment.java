package commercePlatform.orderService.domain.PaymentStrategy;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CashPayment extends Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.85)); // 15% de descuento pagando en efectivo
    }
}
