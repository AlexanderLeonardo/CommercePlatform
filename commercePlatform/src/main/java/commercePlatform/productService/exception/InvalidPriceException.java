package commercePlatform.productService.exception;

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException() {

        super("Product price must be greater than zero");
    }
}
