package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.OrderItem;
import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.domain.PaymentStrategy.CreditCardPayment;
import commercePlatform.orderService.exception.CannotAddOrderItemException;
import commercePlatform.orderService.gateway.InMemoryOrderGateway;
import commercePlatform.orderService.gateway.OrderGateway;
import commercePlatform.orderService.service.CancelOrderService;
import commercePlatform.productService.domain.Product;
import commercePlatform.userService.domain.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CancelOrderTest {

    OrderGateway orderGateway = new InMemoryOrderGateway();
    CancelOrderService cancelOrderService = new CancelOrderService(orderGateway);

    @Test
    void shouldNotAddOrderItemInCancelledState(){
        Order orderJessy = new Order(1L, 1L, "Jessy", "Jessy.pickman@gmail.com",BigDecimal.ZERO, new CreditCardPayment());
        OrderItem orderItemMouse = new OrderItem(1L, 1L, "Mouse", BigDecimal.valueOf(160), 3);
        cancelOrderService.cancelOrder(orderJessy);
        assertThrows(CannotAddOrderItemException.class,
                () -> cancelOrderService.addOrderItem(orderJessy, orderItemMouse));
    }
}
