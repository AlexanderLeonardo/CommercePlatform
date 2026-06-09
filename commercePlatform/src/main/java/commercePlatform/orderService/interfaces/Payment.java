package commercePlatform.orderService.interfaces;

import java.math.BigDecimal;

public interface Payment {

    BigDecimal applyDiscount(BigDecimal total);
}
