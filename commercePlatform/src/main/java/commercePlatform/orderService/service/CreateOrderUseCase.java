package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductNotFoundException;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final UserGateway userGateway;

    public CreateOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway, UserGateway userGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
        this.userGateway = userGateway;
    }

    public Order createOrder(Order order){
        configUserInformation(order);
        configOrderItems(order);
        order.setStatus(OrderStatus.CREATED);  // Pedido creado
        order.updateOrderTotal();              // Actualización del total del pedido
        return this.orderGateway.saveOrder(order);
    }

    public List<Order> getAllOrders(){
        return this.orderGateway.getAllOrders();
    }

    public Optional<Order> getOrderById(Long idOrder){
        return this.orderGateway.findById(idOrder);
    }

    public void configOrderItems(Order order){
        for(OrderItem item: order.getItems()){
            Product product = productGateway.findById(item.getProductId()).orElseThrow(() -> new ProductNotFoundException(item.getProductId()));
            item.setProductName(product.getName());
            item.setProductPrice(product.getPrice());
        }
    }

    public void configUserInformation(Order order){
        User user = userGateway.getUserById(order.getUserId()).orElseThrow(() -> new UserNotFoundException(order.getUserId()));
        order.setUserName(user.getName());
        order.setUserEmail(user.getEmail());
    }

    public void deleteOrder(Long orderId){
        orderGateway.deleteOrder(orderId);
    }

}
