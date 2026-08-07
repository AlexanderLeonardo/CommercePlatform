package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;

import java.util.List;

public class CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public CreateOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order createOrder(Order order){
        return this.orderGateway.saveOrder(order);
    }

    public List<Order> getAllOrders(){
        return this.orderGateway.getAllOrders();
    }

}
