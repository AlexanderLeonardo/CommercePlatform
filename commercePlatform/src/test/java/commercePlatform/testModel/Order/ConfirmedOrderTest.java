package commercePlatform.testModel.Order;

import commercePlatform.orderService.domain.OrderItem;
import commercePlatform.orderService.domain.Order;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.PaymentStrategy.CashPayment;
import commercePlatform.orderService.domain.PaymentStrategy.CreditCardPayment;
import commercePlatform.orderService.domain.PaymentStrategy.MercadoPagoPayment;
import commercePlatform.orderService.exception.CannotCancelOrderException;
import commercePlatform.orderService.exception.CannotModifyOrderException;
import commercePlatform.orderService.exception.EmptyOrderException;
import commercePlatform.orderService.exception.InsufficientStockException;
import commercePlatform.orderService.gateway.InMemoryOrderGateway;
import commercePlatform.orderService.gateway.OrderGateway;
import commercePlatform.orderService.service.ConfirmedOrderService;
import commercePlatform.productService.domain.Product;
import commercePlatform.userService.domain.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfirmedOrderTest {

    OrderGateway gateway = new InMemoryOrderGateway();
    ConfirmedOrderService service = new ConfirmedOrderService(gateway);

    @Test
    void shouldNotConfirmEmptyOrder(){
        Order orderCastiel = new Order(1L, 1L, "Castiel", "cass.angel@gmail.com", BigDecimal.ZERO, new CashPayment());
        assertThrows(EmptyOrderException.class, () -> service.confirmedOrder(orderCastiel));
    }

    @Test
    void shouldConfirmOrderSuccessfully(){
        Order orderSam = new Order(2L, 2L, "Sam", "sam.winchester@gmail.com",  BigDecimal.ZERO, new CreditCardPayment());
        OrderItem orderItemHeadphones = new OrderItem(1L, 1L, "Headphones", BigDecimal.valueOf(180), 1);
        OrderItem orderItemPendrive = new OrderItem(2L, 2L, "Pendrive", BigDecimal.valueOf(10), 2);
        orderSam.addOrderItem(orderItemHeadphones);
        orderSam.addOrderItem(orderItemPendrive);
        service.confirmedOrder(orderSam);
        assertEquals(OrderStatus.CONFIRMED, orderSam.getStatus());
    }

    @Test
    void shouldNotModifyOrderInConfirmedState(){
        Order orderCrowlie = new Order(3L, 3L, "Crowlie","crowlie.kingofhell@gmail.com", BigDecimal.ZERO, new CashPayment());
        OrderItem orderItemSmartTv = new OrderItem(3L, 3L, "SmartTv",BigDecimal.valueOf(430), 1);
        orderCrowlie.addOrderItem(orderItemSmartTv);
        service.confirmedOrder(orderCrowlie);
        assertThrows(CannotModifyOrderException.class,
                () -> service.modifyOrderItemWithId(orderCrowlie, 3L, 3));
    }

    @Test
    void shouldNotCancelOrderInConfirmedState(){
        Order orderKevin = new Order(4L, 4L, "Kevin", "kevin.profeta@gmail.com", BigDecimal.ZERO, new MercadoPagoPayment());
        OrderItem orderItemLaptop = new OrderItem(4L, 4L,"Laptop", BigDecimal.valueOf(800), 1);
        orderKevin.addOrderItem(orderItemLaptop);
        service.confirmedOrder(orderKevin);
        assertThrows(CannotCancelOrderException.class,
                () -> service.cancelOrder(orderKevin));
    }

    @Test
    void shouldApplyDiscountForPaymentStrategy(){
        Order orderKevin = new Order(5L, 4L, "Kevin", "kevin.profeta@gmail.com", BigDecimal.ZERO, new CashPayment());
        OrderItem orderItemMouse = new OrderItem(5L, 5L, "Mouse", BigDecimal.valueOf(25), 3);
        orderKevin.addOrderItem(orderItemMouse);
        service.confirmedOrder(orderKevin);
        assertEquals(BigDecimal.valueOf(63.75), orderKevin.getTotal());
    }
}
