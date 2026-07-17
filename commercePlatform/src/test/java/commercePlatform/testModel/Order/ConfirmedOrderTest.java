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
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConfirmedOrderTest {

    @Test
    void shouldNotConfirmEmptyOrder(){
        Order orderCastiel = new Order(1L, 1L, "Castiel", "cass.angel@gmail.com", BigDecimal.ZERO, new CashPayment());
        assertThrows(EmptyOrderException.class, orderCastiel::confirmedOrder);
    }

    @Test
    void shouldConfirmOrderSuccessfully(){
        Order orderSam = new Order(2L, 2L, "Sam", "sam.winchester@gmail.com",  BigDecimal.ZERO, new CreditCardPayment());
        OrderItem orderItemHeadphones = new OrderItem(1L, 1L, "Headphones", BigDecimal.valueOf(180), 1);
        OrderItem orderItemPendrive = new OrderItem(2L, 2L, "Pendrive", BigDecimal.valueOf(10), 2);
        orderSam.addOrderItem(orderItemHeadphones);
        orderSam.addOrderItem(orderItemPendrive);
        orderSam.confirmedOrder();
        assertEquals(OrderStatus.CONFIRMED, orderSam.getStatus());
    }

    @Test
    void shouldNotModifyOrderInConfirmedState(){
        Order orderCrowley = new Order(3L, 3L, "Crowley","crowlie.kingofhell@gmail.com", BigDecimal.ZERO, new CashPayment());
        OrderItem orderItemSmartTv = new OrderItem(3L, 3L, "SmartTv",BigDecimal.valueOf(430), 1);
        orderCrowley.addOrderItem(orderItemSmartTv);
        orderCrowley.confirmedOrder();
        assertThrows(CannotModifyOrderException.class,
                () -> orderCrowley.modifyOrderItemWithId(3L, 3));
    }

    @Test
    void shouldNotCancelOrderInConfirmedState(){
        Order orderKevin = new Order(4L, 4L, "Kevin", "kevin.profeta@gmail.com", BigDecimal.ZERO, new MercadoPagoPayment());
        OrderItem orderItemLaptop = new OrderItem(4L, 4L,"Laptop", BigDecimal.valueOf(800), 1);
        orderKevin.addOrderItem(orderItemLaptop);
        orderKevin.confirmedOrder();
        assertThrows(CannotCancelOrderException.class,
                orderKevin::cancelOrder);
    }

    @Test
    void shouldApplyDiscountForPaymentStrategy(){
        Order orderKevin = new Order(5L, 4L, "Kevin", "kevin.profeta@gmail.com", BigDecimal.ZERO, new CashPayment());
        OrderItem orderItemMouse = new OrderItem(5L, 5L, "Mouse", BigDecimal.valueOf(120), 3);
        orderKevin.addOrderItem(orderItemMouse);
        orderKevin.confirmedOrder();
        assertEquals(BigDecimal.valueOf(306), orderKevin.getTotal());
    }
}
