package commercePlatform.orderService.domain.PaymentStrategy;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CashPayment implements Payment {

    @Override
    public BigDecimal payWithADiscountApplied(BigDecimal total) {
        System.out.print("The Payment was made in CASH \n");
        return total.multiply(BigDecimal.valueOf(0.85)); // 15% de descuento pagando en efectivo
    }
}
