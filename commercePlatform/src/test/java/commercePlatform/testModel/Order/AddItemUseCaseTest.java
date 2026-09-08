package commercePlatform.testModel.Order;

import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.service.AddItemUseCase;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductDisabledException;
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
        * Caso a testear: el producto a agregar en el pedido tiene stock disponible y está activo.
        *                 Se agrega sin problema.
        * */
        Long idNotebook = 1L;
        Long idOrder = 1L;
        Product notebook = new Product(idNotebook, "Notebook", "Notebook HP 512 GB SSD", BigDecimal.valueOf(1000), 15, true);
        OrderItemRequest orderItemRequest = new OrderItemRequest(idNotebook, 1);
        Order order = new Order(idOrder, 1L, "Chuck", "Chuck.god@gmail.com", OrderStatus.CREATED, BigDecimal.ZERO, new ArrayList<>());

        when(productGateway.findById(orderItemRequest.productId())).thenReturn(Optional.of(notebook));

        addItemUseCase.addOrderItem(order, orderItemRequest);
        verify(orderGateway).saveOrder(order);
        assertEquals(1, order.quantityOfProducts());
    }

    @Test
    void shouldNotAddOrderItemForZeroStockInProduct(){
        /*
        * Caso a testear: el producto a agregar en el pedido se encuentra sin stock disponible (cantidad cero).
        *                 No se debe agregar al pedido, y debe arrojar una excepción.
        * */
        Long idTablet = 2L;
        Long idOrder = 2L;
        Product tablet = new Product(idTablet, "Tablet", "Tablet Apple 1TB", BigDecimal.valueOf(120), 0, true);
        OrderItemRequest orderItemRequest = new OrderItemRequest(idTablet, 1);
        Order order = new Order(idOrder, 2L, "Castiel", "Cass.heavensangel@gmail.com", OrderStatus.CREATED, BigDecimal.ZERO, new ArrayList<>());

        when(productGateway.findById(orderItemRequest.productId())).thenReturn(Optional.of(tablet));

        assertThrows(ZeroStockException.class,
                () -> addItemUseCase.addOrderItem(order, orderItemRequest));
    }

    @Test
    void shouldNotAddOrderItemForInactiveProduct(){
        /*
         * Caso a testear: el producto a agregar en el pedido se encuentra inactivo (flag active = false).
         *                 No se debe agregar al pedido, y debe arrojar una excepción.
         * */
        Long idPcKeyboard = 3L;
        Long idOrder = 3L;
        Product pcKeyboard = new Product(idPcKeyboard, "PC Keyboard", "Gaming Keyboard for PC", BigDecimal.valueOf(130), 5, false);
        OrderItemRequest orderItemRequest = new OrderItemRequest(idPcKeyboard, 2);
        Order order = new Order(idOrder, 3L, "Pepe", "Pepe.lui@gmail.com", OrderStatus.CREATED, BigDecimal.ZERO, new ArrayList<>());

        when(productGateway.findById(orderItemRequest.productId())).thenReturn(Optional.of(pcKeyboard));

        assertThrows(ProductDisabledException.class,
                () -> addItemUseCase.addOrderItem(order, orderItemRequest));
    }
}
