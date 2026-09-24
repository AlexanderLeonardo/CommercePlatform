package commercePlatform.productService.exception;

import commercePlatform.exceptions.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(Long idProduct) {
        super("Not found a product with id: " + idProduct);
    }
}
