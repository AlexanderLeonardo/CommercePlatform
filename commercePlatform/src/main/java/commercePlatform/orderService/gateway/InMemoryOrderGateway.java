package commercePlatform.orderService.gateway;

import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.domain.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderGateway implements OrderGateway {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order saveOrder(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public void addOrderItem(Order order, OrderItem orderItem) {
        order.addOrderItem(orderItem);
    }

    @Override
    public int quantityOfProducts(Order order) {
        return order.quantityOfProducts();
    }

    @Override
    public BigDecimal updatedOrderTotal(Order order) {
        return order.getTotal();
    }

    @Override
    public void confirmedOrder(Order order) {
        order.confirmedOrder();
    }

    @Override
    public Optional<OrderItem> findOrderItemById(Order order, Long id) {
        return order.findOrderItemById(id);
    }

    @Override
    public void modifyOrderItemWithId(Order order, Long idOrderItem, Integer newQuantity) {
        order.modifyOrderItemWithId(idOrderItem, newQuantity);
    }

    @Override
    public void cancelOrder(Order order) {
        order.cancelOrder();
    }
}
