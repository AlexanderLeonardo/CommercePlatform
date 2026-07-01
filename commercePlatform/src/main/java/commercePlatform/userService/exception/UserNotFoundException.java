package commercePlatform.userService.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long idUser) {
        super("Not found a user with id: " + idUser);
    }
}
