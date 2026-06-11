package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

public interface ProductGateway {

    Product saveProduct(Product product);

    Product decreaseStock(Product product, int quantity);

    Product deactivateProduct(Product product);
}
