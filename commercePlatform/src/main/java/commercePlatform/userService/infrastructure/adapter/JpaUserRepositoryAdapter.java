package commercePlatform.userService.infrastructure.adapter;

import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.infrastructure.entity.UserEntity;
import commercePlatform.userService.infrastructure.mapper.UserEntityMapper;
import commercePlatform.userService.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepositoryAdapter implements UserGateway {

    private final UserEntityMapper mapper;
    private final JpaUserRepository repository;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserEntityMapper userEntityMapper) {
        this.repository = jpaUserRepository;
        this.mapper = userEntityMapper;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        repository.save(userEntity);
        return mapper.toDomain(userEntity);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
