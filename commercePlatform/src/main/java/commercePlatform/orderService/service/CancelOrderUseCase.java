package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderUseCase {

    private final OrderGateway orderGateway;

    public CancelOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order cancelOrder(Order order){
        order.cancelOrder();
        return this.orderGateway.saveOrder(order);
    }


}
