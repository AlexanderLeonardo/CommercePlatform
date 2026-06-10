package commercePlatform.testModel.OrderItem;

import commercePlatform.orderService.gateway.InMemoryOrderItemGateway;
import commercePlatform.orderService.gateway.OrderItemGateway;
import commercePlatform.orderService.domain.OrderItem;
import commercePlatform.orderService.service.CreateOrderItemService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrderItemTest {

    OrderItemGateway orderItemGateway = new InMemoryOrderItemGateway();
    CreateOrderItemService createOrderItemService = new CreateOrderItemService(orderItemGateway);

    @Test
    void shouldCreateOrderItemSuccessfully(){
        OrderItem orderItemSmartphone = new OrderItem(1L, 1L, "Smartphone",BigDecimal.valueOf(300), 2);
        OrderItem createdOrderItem = createOrderItemService.createOrderItem(orderItemSmartphone);
        assertEquals("Smartphone", createdOrderItem.productName());

    }

    @Test
    void quantityGreaterThanZero(){
        OrderItem orderItemTablet = new OrderItem(2L, 2L, "Tablet", BigDecimal.valueOf(250),2);
        Integer quantityIsGreaterThanZero = createOrderItemService.orderQuantity(orderItemTablet);
        assertEquals(2, quantityIsGreaterThanZero);
    }

    @Test
    void calculateSubtotal(){
        OrderItem orderItemHeadphones = new OrderItem(3L, 3L, "Headphones",BigDecimal.valueOf(65.5), 3);
        BigDecimal subtotalOrderItem = createOrderItemService.calculateSubtotal(orderItemHeadphones);
        assertEquals(BigDecimal.valueOf(196.5), subtotalOrderItem);
    }
}
