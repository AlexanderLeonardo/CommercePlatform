package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.OrderItem;
import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.gateway.OrderGateway;

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
