package commercePlatform.userService.infrastructure.adapter;

import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.infrastructure.entity.UserEntity;
import commercePlatform.userService.infrastructure.mapper.UserEntityMapper;
import commercePlatform.userService.infrastructure.repository.JpaUserRepository;
import org.springframework.stereotype.Repository;

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
        /* Implementar lógica que verifique si existe un usuario ya creado con la misma cuenta de mail
        * configurada como atributo */
    }
}
