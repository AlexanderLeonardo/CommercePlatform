package commercePlatform.productService.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super("Insufficient stock to deduct the specified quantity");
    }
}
