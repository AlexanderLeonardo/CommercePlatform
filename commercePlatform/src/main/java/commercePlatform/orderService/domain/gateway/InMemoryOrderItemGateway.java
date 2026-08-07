package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.OrderItem;

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
    public List<OrderItem> getAllOrderItems() {
        return orderItems;
    }
}
