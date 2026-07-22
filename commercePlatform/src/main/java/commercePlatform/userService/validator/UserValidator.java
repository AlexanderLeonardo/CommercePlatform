package commercePlatform.userService.validator;

import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserGateway userGateway;

    public UserValidator(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void validateNewUser(User user){
        if(userGateway.existsByEmail(user.getEmail())){
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }
    }
}
