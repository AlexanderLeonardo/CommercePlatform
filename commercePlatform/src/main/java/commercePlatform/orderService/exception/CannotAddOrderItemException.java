package commercePlatform.orderService.exception;

public class CannotAddOrderItemException extends RuntimeException {
    public CannotAddOrderItemException(String state) {
        super("Cannot add the order item if the order status is: " + state);
    }
}
