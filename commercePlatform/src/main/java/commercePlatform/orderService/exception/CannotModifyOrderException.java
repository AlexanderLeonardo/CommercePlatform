package commercePlatform.orderService.exception;

public class CannotModifyOrderException extends RuntimeException {
    public CannotModifyOrderException(String state) {
        super("The order cannot be modified if its status is: " + state);
    }
}
