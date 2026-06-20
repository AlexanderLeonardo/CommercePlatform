package commercePlatform.productService.domain.service;

import commercePlatform.productService.api.validator.ProductValidator;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductGateway productGateway;
    private final ProductValidator productValidator;

    public ProductService(ProductGateway productGateway, ProductValidator productValidator) {
        this.productGateway = productGateway;
        this.productValidator = productValidator;
    }

    public Product createProduct(Product product){
        productValidator.validate(product);
        return productGateway.createProduct(product);
    }

    public List<Product> getAllProducts(){
        return productGateway.getAllProducts();
    }
}
