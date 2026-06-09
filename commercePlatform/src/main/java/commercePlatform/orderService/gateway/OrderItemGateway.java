package commercePlatform.orderService.gateway;

import commercePlatform.orderService.domain.OrderItem;

import java.math.BigDecimal;

public interface OrderItemGateway {

    OrderItem saveOrderItem(OrderItem orderItem);

    Integer orderQuantity(OrderItem orderItem);

    BigDecimal calculateSubtotal(OrderItem orderItem);
}
