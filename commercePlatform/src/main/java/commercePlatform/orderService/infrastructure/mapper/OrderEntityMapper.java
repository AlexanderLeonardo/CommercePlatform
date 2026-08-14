package commercePlatform.orderService.infrastructure.mapper;

import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.infrastructure.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper {

    private final OrderItemEntityMapper orderItemEntityMapper;

    public OrderEntityMapper(OrderItemEntityMapper orderItemEntityMapper) {
        this.orderItemEntityMapper = orderItemEntityMapper;
    }

    public OrderEntity toEntity(Order order){

        return new OrderEntity(order.getId(),
                order.getUserId(),
                order.getUserName(),
                order.getUserEmail(),
                order.getStatus(),
                order.getTotal(),
                order.getItems().stream()
                        .map(orderItemEntityMapper::toEntity)
                        .toList());
    }

    public Order toDomain(OrderEntity orderEntity){

        return new Order(orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getUserName(),
                orderEntity.getUserEmail(),
                orderEntity.getStatus(),
                orderEntity.getTotal(),
                orderEntity.getItems().stream()
                        .map(orderItemEntityMapper::toDomain)
                        .toList());
    }
}
