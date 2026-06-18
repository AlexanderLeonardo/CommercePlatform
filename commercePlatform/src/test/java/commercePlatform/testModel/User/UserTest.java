package commercePlatform.testModel.User;

import commercePlatform.userService.exception.EmailAlreadyRegisteredException;
import commercePlatform.userService.gateway.InMemoryUserGateway;
import commercePlatform.userService.gateway.UserGateway;
import commercePlatform.userService.domain.User;
import commercePlatform.userService.service.CreateUserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {

    UserGateway userGateway = new InMemoryUserGateway();
    CreateUserService createUserService = new CreateUserService(userGateway);

    @Test
    void shouldCreateUserSuccessfully() {
        User alex = new User(Long.valueOf(1), "Alexander", "alex.quinhonez@gmail.com", "siempre viva 123");
        assertEquals("Alexander", alex.getName());
    }

    @Test
    void shouldNotCreateUserWithEmailAlreadyExist(){
        User dean = new User(Long.valueOf(2), "Dean", "dean.winchester@gmail.com", "kansas 666");
        User userSaved = createUserService.createUser(dean);
        System.out.print("First user create: " + userSaved.getName());
        User sam= new User(Long.valueOf(3), "Sam", "dean.winchester@gmail.com", "oklahoma 742" );
        assertThrows(EmailAlreadyRegisteredException.class,
                () -> createUserService.createUser(sam));
    }

}
