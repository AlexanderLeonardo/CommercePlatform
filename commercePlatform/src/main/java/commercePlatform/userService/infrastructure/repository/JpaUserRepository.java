package commercePlatform.userService.infrastructure.repository;

import commercePlatform.userService.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
