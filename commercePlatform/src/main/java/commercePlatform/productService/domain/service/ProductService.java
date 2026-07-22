package commercePlatform.productService.domain.service;

import commercePlatform.productService.api.dto.request.PatchProductRequest;
import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductGateway productGateway;
    //private final ProductValidator productValidator;

    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product createProduct(Product product) {
        return productGateway.saveProduct(product);
    }

    public List<Product> getAllProducts() {
        return productGateway.getAllProducts();
    }

    public Optional<Product> getProductById(Long idProduct) {
        return productGateway.findById(idProduct);
    }

    public Product updateProduct(Product oldProduct, ProductRequest newProduct) {
        oldProduct.setName(newProduct.getName());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.changePrice(newProduct.getPrice());
        oldProduct.changeStock(newProduct.getStock());
        oldProduct.setActive(newProduct.isActive());
        return productGateway.saveProduct(oldProduct);
    }

    public Product partialUpdateProduct(Product oldProduct, PatchProductRequest newProduct) {
        if (newProduct.getName() != null) {
            oldProduct.setName(newProduct.getName());
        }
        if (newProduct.getDescription() != null) {
            oldProduct.setDescription(newProduct.getDescription());
        }
        if (newProduct.getPrice() != null) {
            oldProduct.changePrice(newProduct.getPrice());
        }
        if(newProduct.getStock() != null){
            oldProduct.changeStock(newProduct.getStock());
        }
        if(newProduct.getActive() != null){
            oldProduct.setActive(newProduct.getActive());
        }
        return productGateway.saveProduct(oldProduct);
    }

    public Product modifyStock(Product oldProduct, int newStock) {
        oldProduct.changeStock(newStock);
        return productGateway.saveProduct(oldProduct);
    }

    public Product decreaseStock(Product oldProduct, int quantity) {
        oldProduct.decreaseStock(quantity);
        return productGateway.saveProduct(oldProduct);
    }

    public Product deactivate(Product oldProduct) {
        oldProduct.deactivate();
        return productGateway.saveProduct(oldProduct);
    }

    public Product activate(Product oldProduct) {
        oldProduct.activate();
        return productGateway.saveProduct(oldProduct);
    }

    public void deleteProduct(Long productId) {
        productGateway.deleteProduct(productId);
    }
}
