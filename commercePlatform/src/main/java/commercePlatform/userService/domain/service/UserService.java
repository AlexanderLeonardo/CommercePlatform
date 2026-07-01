package commercePlatform.userService.domain.service;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.exception.UserNotFoundException;
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

    public User getUserById(Long idUser){
        return userGateway.getUserById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
    }

    public User updateUser(Long idUser, User updatedUser){
        User saveUpdateUser = getUserById(idUser);
        verifyEmailAlreadyRegisteredForUpdate(idUser, updatedUser.getEmail());
        saveUpdateUser.setName(updatedUser.getName());
        saveUpdateUser.setEmail(updatedUser.getEmail());
        saveUpdateUser.setAddress(updatedUser.getAddress());
        return userGateway.saveUser(saveUpdateUser);
    }

    public void deleteUser(Long idUser){
        getUserById(idUser);
        userGateway.deleteUser(idUser);
    }
}
