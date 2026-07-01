package commercePlatform.productService.domain.service;

import commercePlatform.productService.api.dto.request.PatchProductRequest;
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

    public Product createProduct(Product product) {
        productValidator.validate(product);
        return productGateway.saveProduct(product);
    }

    public List<Product> getAllProducts() {
        return productGateway.getAllProducts();
    }

    public Product getProductById(Long idProduct) {

        return productGateway.findById(idProduct).orElseThrow(() -> new ProductNotFoundException(idProduct));
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Product updateProduct = getProductById(productId);
        updateProduct.setName(updatedProduct.getName());
        updateProduct.setDescription(updatedProduct.getDescription());
        updateProduct.setPrice(updatedProduct.getPrice());
        updateProduct.setStock(updatedProduct.getStock());
        updateProduct.setActive(updatedProduct.isActive());
        productValidator.validate(updateProduct);
        return productGateway.saveProduct(updateProduct);
    }

    public Product partialUpdateProduct(Long productId, PatchProductRequest patchProductRequest) {
        Product partialUpdateProduct = getProductById(productId);
        if (patchProductRequest.getName() != null) {
            partialUpdateProduct.setName(patchProductRequest.getName());
        }
        if (patchProductRequest.getDescription() != null) {
            partialUpdateProduct.setDescription(patchProductRequest.getDescription());
        }
        if (patchProductRequest.getPrice() != null) {
            partialUpdateProduct.setPrice(patchProductRequest.getPrice());
        }
        productValidator.validate(partialUpdateProduct);
        return productGateway.saveProduct(partialUpdateProduct);
    }

    public Product modifyStock(Long productId, int newStock) {
        Product updateStockProduct = getProductById(productId);
        updateStockProduct.setStock(newStock);
        productValidator.validate(updateStockProduct);
        return productGateway.saveProduct(updateStockProduct);
    }

    public Product decreaseStock(Long productId, int quantity) {
        Product decreaseStockProduct = getProductById(productId);
        decreaseStockProduct.decreaseStock(quantity);
        productValidator.validate(decreaseStockProduct);
        return productGateway.saveProduct(decreaseStockProduct);
    }

    public Product deactivate(Long productId) {
        Product deactivateProduct = getProductById(productId);
        deactivateProduct.deactivate();
        return productGateway.saveProduct(deactivateProduct);
    }

    public Product activate(Long productId) {
        Product activateProduct = getProductById(productId);
        activateProduct.activate();
        return productGateway.saveProduct(activateProduct);
    }

    public void deleteProduct(Long productId) {
        getProductById(productId);
        productGateway.deleteProduct(productId);
    }
}
