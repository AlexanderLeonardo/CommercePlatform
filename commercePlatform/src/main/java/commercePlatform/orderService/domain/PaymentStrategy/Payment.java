package commercePlatform.orderService.domain.PaymentStrategy;

import java.math.BigDecimal;

public interface Payment {

    /* ENUM para cuando se reciba la forma de pago desde el cliente Postman */
    public BigDecimal payWithADiscountApplied(BigDecimal total);
}
