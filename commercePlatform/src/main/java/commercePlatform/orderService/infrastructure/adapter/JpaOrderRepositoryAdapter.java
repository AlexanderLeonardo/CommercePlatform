package commercePlatform.orderService.infrastructure.adapter;

import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.infrastructure.entity.OrderEntity;
import commercePlatform.orderService.infrastructure.mapper.OrderEntityMapper;
import commercePlatform.orderService.infrastructure.repository.JpaOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepositoryAdapter implements OrderGateway {

    private final OrderEntityMapper mapper;
    private final JpaOrderRepository repository;

    public JpaOrderRepositoryAdapter(OrderEntityMapper mapper, JpaOrderRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = mapper.toEntity(order);
        OrderEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }
}
