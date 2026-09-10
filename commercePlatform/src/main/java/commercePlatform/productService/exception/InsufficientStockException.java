package commercePlatform.productService.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String nameProduct) {
        super("The product named " + nameProduct + " have insufficient stock to deduct the specified quantity");
    }
}
