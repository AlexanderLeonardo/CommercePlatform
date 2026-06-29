package commercePlatform.userService.domain.gateway;

import commercePlatform.userService.domain.model.User;

public interface UserGateway {

    User saveUser(User user);
}
