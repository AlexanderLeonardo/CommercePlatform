package commercePlatform.orderService.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String productName) {
        super("Insufficient stock for the product named: " + productName);
    }
}
