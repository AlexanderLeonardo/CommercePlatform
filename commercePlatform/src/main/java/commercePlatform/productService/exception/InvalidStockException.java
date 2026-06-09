package commercePlatform.productService.exception;

public class InvalidStockException extends RuntimeException {

    public InvalidStockException() {

        super("Product stock cannot be negative");
    }
}
