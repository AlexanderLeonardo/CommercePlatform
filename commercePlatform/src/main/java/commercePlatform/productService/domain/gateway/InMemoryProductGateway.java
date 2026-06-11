package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductGateway implements ProductGateway {

    private final List<Product> products = new ArrayList<>();

    @Override
    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public Product decreaseStock(Product product, int quantity) {
        product.decreaseStock(quantity);
        return product;
    }

    @Override
    public Product deactivateProduct(Product product) {
        product.deactivate();
        return product;
    }
}
