package commercePlatform.orderService.domain.PaymentStrategy;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class MercadoPagoPayment extends Payment {

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.95)); // 5% de descuento pagando con Mercado Pago
    }
}
