package commercePlatform.productService.api.mapper;

import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.api.dto.response.ProductResponse;
import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductRequest request){

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.changePrice(request.getPrice());
        product.changeStock(request.getStock());
        product.setActive(request.isActive());
        return product;
    }

    public ProductResponse toResponse(Product product){

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.isActive()
        );
    }
}
