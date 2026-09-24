package commercePlatform.userService.exception;

import commercePlatform.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long idUser) {
        super("Not found a user with id: " + idUser);
    }
}
