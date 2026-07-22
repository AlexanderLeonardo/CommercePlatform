package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.gateway.OrderGateway;

public class CancelOrderService {

    private final OrderGateway orderGateway;

    public CancelOrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void addOrderItem(Order order, OrderItem orderItem){
        this.orderGateway.addOrderItem(order, orderItem);
    }

    public void cancelOrder(Order order){
        this.orderGateway.cancelOrder(order);
    }
}
