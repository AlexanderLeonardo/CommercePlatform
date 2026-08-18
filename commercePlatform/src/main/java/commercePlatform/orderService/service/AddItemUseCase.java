package commercePlatform.orderService.service;

import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddItemUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;

    public AddItemUseCase(OrderGateway orderGateway, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
    }

    public Order addOrderItem(Order order, OrderItemRequest orderItemRequest){
        Product product = productGateway.findById(orderItemRequest.productId())
                                        .orElseThrow(() -> new ProductNotFoundException(orderItemRequest.productId()));
        OrderItem newItem = new OrderItem();
        newItem.setProductId(product.getId());
        newItem.setProductName(product.getName());
        newItem.setProductPrice(product.getPrice());
        newItem.setQuantity(orderItemRequest.quantity());
        order.addOrderItem(newItem);
        return this.orderGateway.saveOrder(order);
    }


}
