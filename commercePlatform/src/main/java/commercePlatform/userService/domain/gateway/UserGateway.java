package commercePlatform.userService.domain.gateway;

import commercePlatform.userService.domain.model.User;

import java.util.List;

public interface UserGateway {

    User saveUser(User user);

    List<User> getAllUsers();
}
