package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderItemGateway;
import commercePlatform.orderService.domain.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemGateway orderItemGateway;

    public OrderItemService(OrderItemGateway orderItemGateway) {
        this.orderItemGateway = orderItemGateway;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return this.orderItemGateway.saveOrderItem(orderItem);
    }

    public List<OrderItem> getAllOrderItems(){
        return this.orderItemGateway.getAllOrderItems();
    }
}
