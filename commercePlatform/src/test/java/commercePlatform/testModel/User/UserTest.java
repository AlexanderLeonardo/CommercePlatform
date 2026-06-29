package commercePlatform.testModel.User;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.domain.gateway.InMemoryUserGateway;
import commercePlatform.userService.domain.gateway.UserGateway;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.domain.service.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    UserGateway userGateway = new InMemoryUserGateway();
    UserService createUserService = new UserService(userGateway);

    @Test
    void shouldCreateUserSuccessfully() {
        User alex = new User(1L, "Alexander", "alex.quinhonez@gmail.com", "siempre viva 123");
        assertEquals("Alexander", alex.getName());
    }

    /*
    @Test
    void shouldNotCreateUserWithEmailAlreadyExist(){
        User dean = new User(2L, "Dean", "dean.winchester@gmail.com", "kansas 666");
        User userSaved = createUserService.createUser(dean);
        System.out.print("First user create: " + userSaved.getName());
        User sam= new User(3L, "Sam", "dean.winchester@gmail.com", "oklahoma 742" );
        assertThrows(EmailAlreadyRegisteredException.class,
                () -> createUserService.createUser(sam));
    }
    */

}
