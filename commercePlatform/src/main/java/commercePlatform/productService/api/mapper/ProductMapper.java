package commercePlatform.productService.api.mapper;

import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.api.dto.response.ProductResponse;
import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductRequest request){

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.changePrice(request.price());
        product.changeStock(request.stock());
        product.setActive(request.active());
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
