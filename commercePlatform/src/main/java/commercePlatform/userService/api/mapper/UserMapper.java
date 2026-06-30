package commercePlatform.userService.api.mapper;

import commercePlatform.userService.api.dto.request.UserRequest;
import commercePlatform.userService.api.dto.response.UserResponse;
import commercePlatform.userService.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserRequest request){

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        return user;
    }

    public UserResponse toResponse(User user){
        return new UserResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAddress());
    }
}
