package commercePlatform.orderService.api.mapper;

import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.api.dto.response.OrderItemResponse;
import commercePlatform.orderService.domain.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toDomain(OrderItemRequest request){

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(request.productId());
        orderItem.setQuantity(request.quantity());
        return orderItem;
    }

    public OrderItemResponse toResponse(OrderItem orderItem){

        return new OrderItemResponse(orderItem.getId(),
                orderItem.getProductName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity());
    }
}
