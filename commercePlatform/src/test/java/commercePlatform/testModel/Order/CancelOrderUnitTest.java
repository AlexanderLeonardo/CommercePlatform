package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.exception.CannotAddOrderItemException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CancelOrderUnitTest {

    @Test
    void shouldCancelOrder(){
        Order orderWalter = new Order(1L, 1L, "Walter", "Heisenberg.meta@gmail.com", BigDecimal.ZERO);
        orderWalter.cancelOrder();
        assertEquals(OrderStatus.CANCELLED, orderWalter.getStatus());
    }

    @Test
    void shouldNotAddOrderItemInCancelledState(){
        Order orderJessy = new Order(2L, 2L, "Jessy", "Jessy.pickman@gmail.com",BigDecimal.ZERO);
        OrderItem orderItemMouse = new OrderItem(1L, 1L, "Mouse", BigDecimal.valueOf(160), 3);
        orderJessy.cancelOrder();
        assertThrows(CannotAddOrderItemException.class,
                () -> orderJessy.addOrderItem(orderItemMouse));
    }
}
