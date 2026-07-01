package commercePlatform.userService.domain.gateway;

import commercePlatform.userService.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserGateway implements UserGateway {

    private final List<User> users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    public boolean existByEmail(String mail) {
        return users.stream().anyMatch(usr -> usr.getEmail().equals(mail));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        User user = users.stream()
                .filter(usr -> usr.getId().equals(id))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

}
