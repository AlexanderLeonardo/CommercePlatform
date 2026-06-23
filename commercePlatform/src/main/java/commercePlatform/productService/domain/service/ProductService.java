package commercePlatform.productService.domain.service;

import commercePlatform.productService.api.validator.ProductValidator;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        return productGateway.saveProduct(product);
    }

    public List<Product> getAllProducts(){
        return productGateway.getAllProducts();
    }
    
    public Product getProductById(Long idProduct) throws NoSuchElementException {

        return productGateway.findById(idProduct).orElseThrow(() -> new ProductNotFoundException(idProduct));
    }

    public Product updateProduct(Long productId, Product updatedProduct){
        Product updateProduct = getProductById(productId);
        updateProduct.setName(updatedProduct.getName());
        updateProduct.setDescription(updatedProduct.getDescription());
        updateProduct.setPrice(updatedProduct.getPrice());
        updateProduct.setStock(updatedProduct.getStock());
        updateProduct.setActive(updatedProduct.isActive());
        return productGateway.saveProduct(updateProduct);
    }

    public Product modifyStock(Long productId, int newStock){
        Product updateStockProduct = getProductById(productId);
        updateStockProduct.setStock(newStock);
        return productGateway.saveProduct(updateStockProduct);
    }
}
