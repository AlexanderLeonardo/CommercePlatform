package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderGateway orderGateway;

    public OrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public List<Order> getAllOrders(){
        return this.orderGateway.getAllOrders();
    }

    public Optional<Order> getOrderById(Long idOrder){
        return this.orderGateway.findById(idOrder);
    }

    public void deleteOrder(Long orderId){
        this.orderGateway.deleteOrder(orderId);
    }

    public List<Order> findByStatus(OrderStatus status) {
        return getAllOrders().stream()
                .filter(order -> order.getStatus().equals(status))
                .toList();
    }
}
