package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.OrderItem;
import java.util.List;

public interface OrderItemGateway {

    void saveOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
    /* Agregar validación en dominio de que QUANTITY debe ser positivo */
}
