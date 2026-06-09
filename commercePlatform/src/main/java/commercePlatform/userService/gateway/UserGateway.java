package commercePlatform.userService.gateway;

import commercePlatform.userService.domain.User;

public interface UserGateway {

    User saveUser(User user);

    boolean existByEmail(String mail);
}
