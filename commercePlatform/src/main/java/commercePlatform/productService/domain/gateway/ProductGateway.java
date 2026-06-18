package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

import java.util.List;

public interface ProductGateway {

    Product createProduct(Product product);
    List<Product> getAllProducts();
}
