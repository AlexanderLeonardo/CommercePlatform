package commercePlatform.orderService.exception;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {

        super("Item quantity cannot be negative");
    }
}
