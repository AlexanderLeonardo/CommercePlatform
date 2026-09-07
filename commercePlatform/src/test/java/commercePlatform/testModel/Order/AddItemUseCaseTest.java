package commercePlatform.testModel.Order;

import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.service.AddItemUseCase;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddItemUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private AddItemUseCase addItemUseCase;

    @Test
    void shouldAddOrderItem(){
        /*
        * Caso a testear: el producto a agregar al pedido tiene stock disponible y está activo.
        *                 Se agrega sin problema.
        * */
        Long idNotebook = 1L;
        Long idOrder = 1L;
        Product notebook = new Product(idNotebook, "Notebook", "Notebook HP 512 GB SSD", BigDecimal.valueOf(1000), 15, true);
        OrderItemRequest orderItemRequest = new OrderItemRequest(idNotebook, 1);
        Order order = new Order(idOrder, 1L, "Chuck", "Chuck.god@gmail.com", OrderStatus.CREATED, new BigDecimal(0), new ArrayList<OrderItem>());

        when(productGateway.findById(orderItemRequest.productId())).thenReturn(Optional.of(notebook));

        addItemUseCase.addOrderItem(order, orderItemRequest);
        verify(orderGateway).saveOrder(order);
        assertEquals(1, order.quantityOfProducts());
    }
}
