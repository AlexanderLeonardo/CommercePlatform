package commercePlatform.userService.domain.service;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
/* Lógica que usa la abstracción */

    private final UserGateway userGateway;

    public UserService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }


    public boolean existByEmail(String mail){
        return getAllUsers().stream().anyMatch( usr -> usr.getEmail().equals(mail));
    }

    public User createUser(User user) {

        //if(userGateway.existByEmail(user.getEmail())){
        //    throw new EmailAlreadyRegisteredException(user.getEmail());
        //}
        if(existByEmail(user.getEmail())){
            throw new EmailAlreadyRegisteredException(user.getEmail());
        }
        return userGateway.saveUser(user);
    }

    public List<User> getAllUsers(){
        return userGateway.getAllUsers();
    }
}
