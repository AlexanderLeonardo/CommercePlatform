package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.OrderStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrderUnitTest {

    Order orderDean = new Order(1L, 1L, "Dean", "dean.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
    OrderItem orderItemTablet = new OrderItem(1L, 1L, "Tablet", BigDecimal.valueOf(250) , 1);
    OrderItem orderItemMonitor = new OrderItem(2L, 2L, "Monitor", BigDecimal.valueOf(150), 2);

    @Test
    void createOrderSuccessfully(){
        assertEquals(OrderStatus.CREATED, orderDean.getStatus());
        assertEquals(0, orderDean.quantityOfProducts());
    }

    @Test
    void preventOrderEmpty(){
        orderDean.addOrderItem(orderItemTablet);
        assertEquals(1, orderDean.quantityOfProducts());
    }

    @Test
    void updateOrderTotalAfterAddOrderItems(){
        orderDean.addOrderItem(orderItemTablet);
        orderDean.addOrderItem(orderItemMonitor);
        assertEquals(BigDecimal.valueOf(550), orderDean.calculateTotal());
        assertEquals(BigDecimal.valueOf(550), orderDean.getTotal());
    }

    @Test
    void shouldCalculateOrderTotalWithoutZerosInDecimals(){
        Order orderSam = new Order(2L, 2L, "Sam", "Sam.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem orderItemNotebook = new OrderItem(3L, 3L, "Notebook", BigDecimal.valueOf(560.40) , 1);
        OrderItem orderItemMouse = new OrderItem(4L, 4L, "Mouse", BigDecimal.valueOf(80.60), 1);
        orderSam.addOrderItem(orderItemNotebook);
        orderSam.addOrderItem(orderItemMouse);
        assertEquals(BigDecimal.valueOf(641), orderSam.getTotal());
        assertEquals(BigDecimal.valueOf(641), orderSam.calculateTotal());
    }

    @Test
    void findOrderItemByIdSuccessfully(){
        orderDean.addOrderItem(orderItemTablet);
        orderDean.addOrderItem(orderItemMonitor);
        OrderItem findOrderItem = orderDean.findOrderItemById(2L).orElseThrow();
        assertEquals("Monitor", findOrderItem.productName());
    }

    @Test
    void shouldModifyOrderInCreateState(){
        orderDean.addOrderItem(orderItemTablet);
        orderDean.addOrderItem(orderItemMonitor);
        orderDean.modifyOrderItemWithId(2L, 6);
        assertEquals(BigDecimal.valueOf(1150), orderDean.getTotal());
        assertEquals(BigDecimal.valueOf(1150), orderDean.calculateTotal());
    }
}

