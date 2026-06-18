package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductGateway implements ProductGateway {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product createProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }


}
