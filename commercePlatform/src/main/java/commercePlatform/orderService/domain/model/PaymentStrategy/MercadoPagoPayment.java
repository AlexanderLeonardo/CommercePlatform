package commercePlatform.orderService.domain.model.PaymentStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MercadoPagoPayment implements Payment {

    @Override
    public BigDecimal payWithADiscountApplied(BigDecimal total) {
        System.out.print("The Payment was made with MERCADO PAGO \n");
        return total.multiply(BigDecimal.valueOf(0.95));  // 5% de descuento pagando con Mercado Pago
    }
}
