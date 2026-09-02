package commercePlatform.productService.exception;

public class ProductDisabledException extends RuntimeException {
    public ProductDisabledException(String nameProduct) {
        super("The product named " + nameProduct + " is disabled");
    }
}
