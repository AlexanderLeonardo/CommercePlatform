package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class ModifyOrderUserCase {

    private final OrderGateway orderGateway;

    public ModifyOrderUserCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order modifyOrder(Order order, Long idOrderItem, Integer newQuantity){
        order.modifyOrderItemWithId(idOrderItem, newQuantity);
        return this.orderGateway.saveOrder(order);
    }
}
