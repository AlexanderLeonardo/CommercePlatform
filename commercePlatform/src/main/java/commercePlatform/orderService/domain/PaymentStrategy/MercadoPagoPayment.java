package commercePlatform.orderService.domain.PaymentStrategy;

import commercePlatform.orderService.interfaces.Payment;

import java.math.BigDecimal;

public class MercadoPagoPayment implements Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.95)); // 5% de descuento pagando con Mercado Pago
    }
}
