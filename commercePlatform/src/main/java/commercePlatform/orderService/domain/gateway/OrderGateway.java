package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderGateway {
    /* Abstacción del dominio (contrato del dominio) */
    Order saveOrder(Order order);
    Optional<Order> findById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}
