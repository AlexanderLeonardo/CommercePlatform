package commercePlatform.userService.gateway;

import commercePlatform.userService.domain.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserGateway implements UserGateway {

    private final List<User> users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public boolean existByEmail(String mail) {
        return users.stream().anyMatch(usr -> usr.getEmail().equals(mail));
    }

}
