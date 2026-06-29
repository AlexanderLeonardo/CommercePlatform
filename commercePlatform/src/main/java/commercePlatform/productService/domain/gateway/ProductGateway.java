package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> findById(Long id);
    void deleteProduct(Long id);
}
