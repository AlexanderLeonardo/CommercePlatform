package commercePlatform.orderService.gateway;

import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Optional;

public interface OrderGateway {
    /* Abstacción del dominio (contrato del dominio) */

    Order saveOrder(Order order);

    void addOrderItem(Order order, OrderItem orderItem);

    int quantityOfProducts(Order order);

    BigDecimal updatedOrderTotal(Order order);

    void confirmedOrder(Order order);

    Optional<OrderItem> findOrderItemById(Order order, Long idOrderItem);

    void modifyOrderItemWithId(Order order, Long idOrderItem, Integer newQuantity);

    void cancelOrder(Order order);
}
