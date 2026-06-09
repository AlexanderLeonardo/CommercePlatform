package commercePlatform.productService.gateway;

import commercePlatform.productService.domain.Product;

public interface ProductGateway {

    Product saveProduct(Product product);

    Product decreaseStock(Product product, int quantity);

    Product deactivateProduct(Product product);
}
