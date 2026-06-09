package commercePlatform.userService.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException(String mail) {

        super("The mail address: " + mail + " is already registered in the system");
    }
}
