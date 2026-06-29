package commercePlatform.userService.domain.service;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;

public class UserService {
/* Lógica que usa la abstracción */

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {

        //if(userGateway.existByEmail(user.getEmail())){
        //    throw new EmailAlreadyRegisteredException(user.getEmail());
        //}

        return userGateway.saveUser(user);
    }
}
