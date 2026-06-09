package commercePlatform.userService.service;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.gateway.UserGateway;
import commercePlatform.userService.domain.User;

public class CreateUserService {
/* Lógica que usa la abstracción */

    private final UserGateway userGateway;

    public CreateUserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {

        if(userGateway.existByEmail(user.getEmail())){
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }

        return userGateway.saveUser(user);
    }
}
