package commercePlatform.orderService.infrastructure.mapper;

import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.infrastructure.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemEntityMapper {

    public OrderItemEntity toEntity(OrderItem orderItem){

        return new OrderItemEntity(orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getProductName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity());
    }

    public OrderItem toDomain(OrderItemEntity orderItem){

        return new OrderItem(orderItem.getId(),
                orderItem.getProductId(),
                orderItem.productName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity());
    }
}
