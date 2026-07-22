package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.gateway.OrderGateway;

public class ConfirmedOrderService {

    private final OrderGateway orderGateway;

    public ConfirmedOrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public void confirmedOrder(Order order){
        orderGateway.confirmedOrder(order);
    }

    public void modifyOrderItemWithId(Order order, Long idOrderItem, Integer newQuantity){
        orderGateway.modifyOrderItemWithId(order, idOrderItem, newQuantity);
    }

    public void cancelOrder(Order order){
        orderGateway.cancelOrder(order);
    }
}
