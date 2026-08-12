package commercePlatform.orderService.infrastructure.adapter;

import commercePlatform.orderService.domain.gateway.OrderItemGateway;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.infrastructure.entity.OrderItemEntity;
import commercePlatform.orderService.infrastructure.mapper.OrderItemEntityMapper;
import commercePlatform.orderService.infrastructure.repository.JpaOrderItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaOrderItemRepositoryAdapter implements OrderItemGateway {

    private final OrderItemEntityMapper mapper;
    private final JpaOrderItemRepository repository;

    public JpaOrderItemRepositoryAdapter(OrderItemEntityMapper mapper, JpaOrderItemRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        OrderItemEntity orderItemEntity = mapper.toEntity(orderItem);
        repository.save(orderItemEntity);
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
