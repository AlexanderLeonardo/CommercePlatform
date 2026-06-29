package commercePlatform.userService.infrastructure.mapper;

import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user){

        return new UserEntity(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress());
    }

    public User toDomain(UserEntity userEntity){

        return new User(userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getAddress());
    }

}
