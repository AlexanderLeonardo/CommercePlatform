package commercePlatform.userService.domain.gateway;

import commercePlatform.userService.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void deleteUser(Long id);

    boolean existsByEmail(String mail);


}
