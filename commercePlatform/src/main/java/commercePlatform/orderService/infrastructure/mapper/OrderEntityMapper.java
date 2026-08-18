package commercePlatform.orderService.infrastructure.mapper;

import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.infrastructure.entity.OrderEntity;
import commercePlatform.orderService.infrastructure.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderEntityMapper {

    private final OrderItemEntityMapper orderItemEntityMapper;

    public OrderEntityMapper(OrderItemEntityMapper orderItemEntityMapper) {
        this.orderItemEntityMapper = orderItemEntityMapper;
    }

    public OrderEntity toEntity(Order order){

        List<OrderItemEntity> itemsEntities = new ArrayList<>();
        for(OrderItem item: order.getItems()){
            itemsEntities.add(orderItemEntityMapper.toEntity(item));
        }

        return new OrderEntity(order.getId(),
                order.getUserId(),
                order.getUserName(),
                order.getUserEmail(),
                order.getStatus(),
                order.getTotal(),
                itemsEntities);
    }

    public Order toDomain(OrderEntity orderEntity){

        List<OrderItem> items = new ArrayList<>();
        for(OrderItemEntity itemEntity: orderEntity.getItems()){
            items.add(orderItemEntityMapper.toDomain(itemEntity));
        }

        return new Order(orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getUserName(),
                orderEntity.getUserEmail(),
                orderEntity.getStatus(),
                orderEntity.getTotal(),
                items);
    }
}
