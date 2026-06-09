package commercePlatform.orderService.exception;

public class CannotCancelOrderException extends RuntimeException {
    public CannotCancelOrderException(String state) {
        super("The order cannot be cancelled if its status is: " + state);
    }
}
