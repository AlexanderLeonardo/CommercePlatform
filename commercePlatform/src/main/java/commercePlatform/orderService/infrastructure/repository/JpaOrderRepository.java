package commercePlatform.orderService.infrastructure.repository;

import commercePlatform.orderService.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
}
