package commercePlatform.productService.service;

import commercePlatform.productService.gateway.ProductGateway;
import commercePlatform.productService.domain.Product;

public class CreateProductService {

    private final ProductGateway productGateway;

    public CreateProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product){
        return productGateway.saveProduct(product);
    }

    public Product decreaseStockProduct(Product product, int quantity){
        return productGateway.decreaseStock(product, quantity);
    }

    public Product deactivateProduct(Product product){
        return productGateway.deactivateProduct(product);
    }
}
