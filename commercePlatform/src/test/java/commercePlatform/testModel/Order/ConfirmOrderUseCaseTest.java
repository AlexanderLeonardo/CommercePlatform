package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.service.ConfirmOrderUseCase;
import commercePlatform.productService.domain.gateway.InventoryGateway;
import commercePlatform.productService.exception.InsufficientStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfirmOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private InventoryGateway inventoryGateway;

    @InjectMocks
    private ConfirmOrderUseCase useCase;

    @Test
    void shouldConfirmOrderAndDiscountStock(){

        Long orderId = 1L;
        Long productIdMouse = 1L;
        Long productIdMonitor = 2L;

        Order order = new Order(orderId, 1L, "Sam", "Sam.winchester@gmail.com", new BigDecimal(0));
        OrderItem mouse = new OrderItem(1L, productIdMouse, "Mouse", BigDecimal.valueOf(20), 2);
        OrderItem monitor = new OrderItem(2L, productIdMonitor, "Monitor", BigDecimal.valueOf(45), 3);
        order.addOrderItem(mouse);
        order.addOrderItem(monitor);

        when(orderGateway.findById(orderId)).thenReturn(Optional.of(order));

        useCase.confirmOrder(orderId);

        verify(inventoryGateway).reserveStock(productIdMouse, 2);
        verify(inventoryGateway).reserveStock(productIdMonitor, 3);
        verify(orderGateway).saveOrder(order);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void shouldNotConfirmOrderAndDiscountStockWhenProductOutOfStock(){

        Long orderId = 2L;
        Long productIdHeadphones = 3L;
        Order order = new Order(orderId, 2L, "Dean", "Dean.winchester@gmail.com", new BigDecimal(0));
        OrderItem headphones = new OrderItem(3L, productIdHeadphones, "Headphones", BigDecimal.valueOf(12), 2);
        order.addOrderItem(headphones);
        doThrow(new InsufficientStockException())
                .when(inventoryGateway)
                .reserveStock(anyLong(), anyInt());

        when(orderGateway.findById(orderId)).thenReturn(Optional.of(order));
        assertThrows(InsufficientStockException.class,
                () -> useCase.confirmOrder(orderId));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }
}
