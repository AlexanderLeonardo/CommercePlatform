package commercePlatform.testModel.Order;

import commercePlatform.orderService.api.dto.request.ConfirmOrderRequest;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.PaymentStrategy.CashPayment;
import commercePlatform.orderService.domain.model.PaymentStrategy.CreditCardPayment;
import commercePlatform.orderService.domain.model.PaymentStrategy.PaymentMethod;
import commercePlatform.orderService.factory.PaymentFactory;
import commercePlatform.orderService.service.ConfirmOrderUseCase;
import commercePlatform.productService.domain.gateway.InventoryGateway;
import commercePlatform.productService.exception.InsufficientStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Mock
    private PaymentFactory paymentFactory;

    @InjectMocks
    private ConfirmOrderUseCase useCase;

    @Test
    void shouldConfirmOrderAndDiscountStock(){

        Long orderId = 1L;
        Long productIdMouse = 1L;
        Long productIdMonitor = 2L;
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(PaymentMethod.CASH);
        Order order = new Order(orderId, 1L, "Sam", "Sam.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem mouse = new OrderItem(1L, productIdMouse, "Mouse", BigDecimal.valueOf(20), 2);
        OrderItem monitor = new OrderItem(2L, productIdMonitor, "Monitor", BigDecimal.valueOf(45), 3);
        order.addOrderItem(mouse);
        order.addOrderItem(monitor);

        when(paymentFactory.getPayment(confirmOrderRequest.paymentMethod())).thenReturn(new CashPayment());

        useCase.confirmOrder(order, confirmOrderRequest);

        verify(inventoryGateway).reserveStock(productIdMouse, 2);
        verify(inventoryGateway).reserveStock(productIdMonitor, 3);
        verify(orderGateway).saveOrder(order);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        assertEquals(BigDecimal.valueOf(148.75), order.getTotal());
    }

    @Test
    void shouldNotConfirmOrderAndDiscountStockWhenProductOutOfStock(){

        Long orderId = 2L;
        Long productIdHeadphones = 3L;
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(PaymentMethod.CREDIT_CARD);
        Order order = new Order(orderId, 2L, "Dean", "Dean.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem headphones = new OrderItem(3L, productIdHeadphones, "Headphones", BigDecimal.valueOf(12), 2);
        order.addOrderItem(headphones);
        doThrow(new InsufficientStockException())
                .when(inventoryGateway)
                .reserveStock(anyLong(), anyInt());

        when(paymentFactory.getPayment(confirmOrderRequest.paymentMethod())).thenReturn(new CreditCardPayment());
        assertThrows(InsufficientStockException.class,
                () -> useCase.confirmOrder(order, confirmOrderRequest));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }
}
