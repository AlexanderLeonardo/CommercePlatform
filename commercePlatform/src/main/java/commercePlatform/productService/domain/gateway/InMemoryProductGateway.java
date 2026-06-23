package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductGateway implements ProductGateway {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = products.stream()
                .filter(prd -> prd.getId().equals(id))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(product);
    }
}
