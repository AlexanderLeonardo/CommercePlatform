package commercePlatform.orderService.infrastructure.repository;

import commercePlatform.orderService.infrastructure.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
