package commercePlatform.testModel.OrderItem;

import commercePlatform.orderService.domain.model.OrderItem;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {

    @Test
    void shouldCreateOrderItemSuccessfully(){
        OrderItem orderItemSmartphone = new OrderItem(1L, 1L, "Smartphone",BigDecimal.valueOf(300), 2);
        assertEquals("Smartphone", orderItemSmartphone.productName());

    }

    @Test
    void quantityGreaterThanZero(){
        OrderItem orderItemTablet = new OrderItem(2L, 2L, "Tablet", BigDecimal.valueOf(250),2);
        assertEquals(2, orderItemTablet.getQuantity());
    }

    @Test
    void calculateSubtotal(){
        OrderItem orderItemHeadphones = new OrderItem(3L, 3L, "Headphones",BigDecimal.valueOf(65.5), 3);
        assertEquals(BigDecimal.valueOf(196.5), orderItemHeadphones.calculateSubtotal());
    }
}
