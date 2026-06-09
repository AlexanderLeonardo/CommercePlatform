package commercePlatform.orderService.exception;

public class EmptyOrderException extends RuntimeException {

    public EmptyOrderException() {
        super("The order cannot be empty; it must contain products");
    }
}
