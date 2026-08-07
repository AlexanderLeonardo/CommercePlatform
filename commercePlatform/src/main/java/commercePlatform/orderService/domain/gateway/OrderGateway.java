package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderGateway {
    /* Abstacción del dominio (contrato del dominio) */
    Order saveOrder(Order order);
    Optional<Order> findById(Long id);
    List<Order> getAllOrders();
}
