package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.OrderItem;
import commercePlatform.orderService.domain.PaymentStrategy.MercadoPagoPayment;
import commercePlatform.orderService.exception.InsufficientStockException;
import commercePlatform.orderService.gateway.InMemoryOrderGateway;
import commercePlatform.orderService.gateway.OrderGateway;
import commercePlatform.orderService.service.ConfirmedOrderService;
import commercePlatform.orderService.service.CreateOrderService;
import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.productService.domain.Product;
import commercePlatform.userService.domain.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateOrderTest {

    OrderGateway orderGateway = new InMemoryOrderGateway();
    CreateOrderService createOrderService = new CreateOrderService(orderGateway);
    Order orderDean = new Order(1L, 1L, "Dean", "dean.winchester@gmail.com", new BigDecimal(0), new MercadoPagoPayment());
    OrderItem orderItemTablet = new OrderItem(1L, 1L, "Tablet", BigDecimal.valueOf(250) , 1);
    OrderItem orderItemMonitor = new OrderItem(2L, 2L, "Monitor", BigDecimal.valueOf(150), 2);

    @Test
    void createOrderSuccessfully(){
        Order orderSaved = createOrderService.createOrder(orderDean);
        assertEquals(OrderStatus.CREATED, orderSaved.getStatus());
        assertEquals(0, orderSaved.quantityOfProducts());
    }

    @Test
    void preventOrderEmpty(){
        createOrderService.addOrderItem(orderDean,orderItemTablet);
        assertEquals(1, createOrderService.quantityOfProducts(orderDean));
    }

    @Test
    void updateOrderTotal(){
        createOrderService.addOrderItem(orderDean,orderItemTablet);
        createOrderService.addOrderItem(orderDean,orderItemMonitor);
        assertEquals(BigDecimal.valueOf(550), createOrderService.updatedOrderTotal(orderDean));
    }

    @Test
    void findOrderItemByIdSuccessfully(){
        createOrderService.addOrderItem(orderDean, orderItemTablet);
        createOrderService.addOrderItem(orderDean, orderItemMonitor);
        OrderItem findOrderItem = createOrderService.findOrderItemById(orderDean, 2L).orElseThrow();
        assertEquals("Monitor", findOrderItem.productName());
    }

    @Test
    void shouldModifyOrderInCreateState(){
        createOrderService.addOrderItem(orderDean, orderItemTablet);
        createOrderService.addOrderItem(orderDean, orderItemMonitor);
        createOrderService.modifyOrderItemWithId(orderDean, 2L, 6);
        assertEquals(BigDecimal.valueOf(1150), createOrderService.updatedOrderTotal(orderDean));
    }

    @Test
    void shouldCancelOrder(){
        createOrderService.addOrderItem(orderDean, orderItemTablet);
        createOrderService.addOrderItem(orderDean, orderItemMonitor);
        createOrderService.cancelOrder(orderDean);
        assertEquals(OrderStatus.CANCELLED, orderDean.getStatus());
    }
}
