package commercePlatform.orderService.gateway;

import commercePlatform.orderService.domain.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderItemGateway implements OrderItemGateway {

    private final List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        return orderItem;
    }

    @Override
    public Integer orderQuantity(OrderItem orderItem) {
        return orderItem.getQuantity();
    }

    @Override
    public BigDecimal calculateSubtotal(OrderItem orderItem) {
        return orderItem.calculateSubtotal();
    }

}
