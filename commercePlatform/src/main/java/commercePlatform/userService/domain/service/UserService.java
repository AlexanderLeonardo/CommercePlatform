package commercePlatform.userService.domain.service;

import commercePlatform.userService.api.dto.request.UserRequest;
import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean existByEmailForUpdate(Long idUserUpdate, String email){
        boolean existByEmailInOtherUsers = false;
        for(User usr: getAllUsers()){
            if(!usr.getId().equals(idUserUpdate)){
                existByEmailInOtherUsers = existByEmailInOtherUsers || usr.getEmail().equals(email);
            }
        }
        return existByEmailInOtherUsers;
    }

    public void verifyEmailAlreadyRegistered(String email){
        if(existByEmail(email)){
            throw new EmailAlreadyRegisteredException(email);
        }
    }

    public void verifyEmailAlreadyRegisteredForUpdate(Long idUserUpdate, String email){
        if(existByEmailForUpdate(idUserUpdate, email)){
            throw new EmailAlreadyRegisteredException(email);
        }
    }

    public User createUser(User user) {
        verifyEmailAlreadyRegistered(user.getEmail());
        return userGateway.saveUser(user);
    }

    public List<User> getAllUsers(){
        return userGateway.getAllUsers();
    }

    public Optional<User> getUserById(Long idUser){
        return userGateway.getUserById(idUser);
    }

    public User updateUser(User oldUser, UserRequest newUser){
        //User saveUpdateUser = getUserById(idUser);
        verifyEmailAlreadyRegisteredForUpdate(oldUser.getId(), newUser.getEmail());
        oldUser.setName(newUser.getName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setAddress(newUser.getAddress());
        return userGateway.saveUser(oldUser);
    }

    public void deleteUser(Long idUser){
        userGateway.deleteUser(idUser);
    }
}
