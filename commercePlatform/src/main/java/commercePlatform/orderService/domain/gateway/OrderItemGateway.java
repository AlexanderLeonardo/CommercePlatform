package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemGateway {

    OrderItem saveOrderItem(OrderItem orderItem);
    List<OrderItem> getAllOrderItems();
    /* Agregar validación en dominio de que QUANTITY debe ser positivo */
}
