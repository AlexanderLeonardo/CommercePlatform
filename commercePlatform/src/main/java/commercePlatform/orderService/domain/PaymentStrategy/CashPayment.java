package commercePlatform.orderService.domain.PaymentStrategy;

import commercePlatform.orderService.interfaces.Payment;

import java.math.BigDecimal;

public class CashPayment implements Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.85)); // 15% de descuento pagando en efectivo
    }
}
