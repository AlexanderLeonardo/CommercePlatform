package commercePlatform.productService.domain.service;

import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductGateway productGateway;

    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product){
        return productGateway.createProduct(product);
    }

    public List<Product> getAllProducts(){
        return productGateway.getAllProducts();
    }
}
