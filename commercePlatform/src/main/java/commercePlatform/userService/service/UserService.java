package commercePlatform.userService.service;

import commercePlatform.userService.api.dto.request.UserRequest;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.validator.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
/* Lógica que usa la abstracción */

    private final UserGateway userGateway;
    private final UserValidator userValidator;

    public UserService(UserGateway userGateway, UserValidator userValidator) {
        this.userGateway = userGateway;
        this.userValidator = userValidator;
    }

    public User createUser(User user) {
        userValidator.validateNewUser(user);
        return userGateway.saveUser(user);
    }

    public List<User> getAllUsers(){
        return userGateway.getAllUsers();
    }

    public Optional<User> getUserById(Long idUser){
        return userGateway.getUserById(idUser);
    }

    public User updateUser(User oldUser, UserRequest newUser){
        oldUser.setName(newUser.getName());
        /* La cuenta de email NO cambia, debe ser la misma con la que se registró el usuario */
        oldUser.setEmail(newUser.getEmail());
        oldUser.setAddress(newUser.getAddress());
        return userGateway.saveUser(oldUser);
    }

    public void deleteUser(Long idUser){
        userGateway.deleteUser(idUser);
    }
}
