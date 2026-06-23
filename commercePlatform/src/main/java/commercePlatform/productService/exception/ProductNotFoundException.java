package commercePlatform.productService.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long idProduct) {
        super("Not found a product with id: " + idProduct);
    }
}
