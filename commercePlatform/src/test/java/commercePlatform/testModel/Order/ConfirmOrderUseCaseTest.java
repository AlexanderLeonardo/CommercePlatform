package commercePlatform.testModel.Order;

import commercePlatform.orderService.api.dto.request.ConfirmOrderRequest;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.PaymentStrategy.CashPayment;
import commercePlatform.orderService.domain.model.PaymentStrategy.CreditCardPayment;
import commercePlatform.orderService.domain.model.PaymentStrategy.MercadoPagoPayment;
import commercePlatform.orderService.domain.model.PaymentStrategy.PaymentMethod;
import commercePlatform.orderService.factory.PaymentFactory;
import commercePlatform.orderService.service.ConfirmOrderUseCase;
import commercePlatform.productService.domain.gateway.InventoryGateway;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.InsufficientStockException;
import commercePlatform.productService.exception.ZeroStockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConfirmOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private InventoryGateway inventoryGateway;

    @Mock
    private PaymentFactory paymentFactory;

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private ConfirmOrderUseCase useCase;

    @Test
    void shouldConfirmOrderAndDiscountStock(){

        Long orderId = 1L;
        Long productIdMouse = 1L;
        Long productIdMonitor = 2L;
        Product mouse = new Product(productIdMouse, "Mouse", "Mouse Genius", BigDecimal.valueOf(20), 30, true);
        Product monitor = new Product(productIdMonitor, "Monitor", "Monitor LG", BigDecimal.valueOf(45), 27, true);
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(PaymentMethod.CASH);
        Order order = new Order(orderId, 1L, "Sam", "Sam.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem itemMouse = new OrderItem(1L, productIdMouse, "Mouse", BigDecimal.valueOf(20), 2);
        OrderItem itemMonitor = new OrderItem(2L, productIdMonitor, "Monitor", BigDecimal.valueOf(45), 3);
        order.addOrderItem(itemMouse);
        order.addOrderItem(itemMonitor);

        when(paymentFactory.getPayment(confirmOrderRequest.paymentMethod())).thenReturn(new CashPayment());
        when(productGateway.findById(productIdMouse)).thenReturn(Optional.of(mouse));
        when(productGateway.findById(productIdMonitor)).thenReturn(Optional.of(monitor));

        useCase.confirmOrder(order, confirmOrderRequest);

        verify(inventoryGateway).reserveStock(mouse, 2);
        verify(inventoryGateway).reserveStock(monitor, 3);
        verify(orderGateway).saveOrder(order);
        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        assertEquals(BigDecimal.valueOf(148.75), order.getTotal());
    }

    @Test
    void shouldNotConfirmOrderAndDiscountStockWhenProductOutOfStock(){

        Long orderId = 2L;
        Long productIdHeadphones = 3L;
        Product headphones = new Product(productIdHeadphones, "Headphones", "Headphones Havit", BigDecimal.valueOf(12), 17, true);
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(PaymentMethod.CREDIT_CARD);
        Order order = new Order(orderId, 2L, "Dean", "Dean.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem itemHeadphones = new OrderItem(3L, productIdHeadphones, "Headphones", BigDecimal.valueOf(12), 2);
        order.addOrderItem(itemHeadphones);
        doThrow(new InsufficientStockException())
                .when(inventoryGateway)
                .reserveStock(any(), anyInt());

        when(paymentFactory.getPayment(confirmOrderRequest.paymentMethod())).thenReturn(new CreditCardPayment());
        when(productGateway.findById(productIdHeadphones)).thenReturn(Optional.of(headphones));
        assertThrows(InsufficientStockException.class,
                () -> useCase.confirmOrder(order, confirmOrderRequest));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void shouldNotConfirmOrderAndDiscountStockWhenProductHaveZeroStock(){
        Long orderId = 1L;
        Long productIdMouse = 1L;
        Product mouse = new Product(productIdMouse, "Mouse", "Mouse Genius", BigDecimal.valueOf(20), 0, true);
        ConfirmOrderRequest confirmOrderRequest = new ConfirmOrderRequest(PaymentMethod.MERCADO_PAGO);
        Order order = new Order(orderId, 1L, "Sam", "Sam.winchester@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());
        OrderItem itemMouse = new OrderItem(1L, productIdMouse, "Mouse", BigDecimal.valueOf(20), 2);
        order.addOrderItem(itemMouse);

        when(paymentFactory.getPayment(confirmOrderRequest.paymentMethod())).thenReturn(new MercadoPagoPayment());
        when(productGateway.findById(productIdMouse)).thenReturn(Optional.of(mouse));

        assertThrows(ZeroStockException.class,
                () -> useCase.confirmOrder(order, confirmOrderRequest));
    }
}
