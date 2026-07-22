package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateOrderService {

    private final OrderGateway orderGateway;

    public CreateOrderService(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public Order createOrder(Order order){
        return this.orderGateway.saveOrder(order);
    }

    public void addOrderItem(Order order, OrderItem orderItem){
        this.orderGateway.addOrderItem(order, orderItem);
    }

    public int quantityOfProducts(Order order){
        return this.orderGateway.quantityOfProducts(order);
    }

    public BigDecimal updatedOrderTotal(Order order){
        return this.orderGateway.updatedOrderTotal(order);
    }

    public Optional<OrderItem> findOrderItemById(Order order, Long id){
        return this.orderGateway.findOrderItemById(order, id);
    }

    public void modifyOrderItemWithId(Order order, Long idOrderItem, Integer newQuantity){
        orderGateway.modifyOrderItemWithId(order, idOrderItem, newQuantity);
    }

    public void cancelOrder(Order order) {
        orderGateway.cancelOrder(order);
    }
}
