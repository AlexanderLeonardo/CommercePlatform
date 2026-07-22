package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderItemGateway;
import commercePlatform.orderService.domain.model.OrderItem;

import java.math.BigDecimal;

public class CreateOrderItemService {

    private final OrderItemGateway orderItemGateway;

    public CreateOrderItemService(OrderItemGateway orderItemGateway) {
        this.orderItemGateway = orderItemGateway;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return this.orderItemGateway.saveOrderItem(orderItem);
    }

    public Integer orderQuantity(OrderItem orderItem){
        return this.orderItemGateway.orderQuantity(orderItem);
    }

    public BigDecimal calculateSubtotal(OrderItem orderItem){
        return this.orderItemGateway.calculateSubtotal(orderItem);
    }
}
