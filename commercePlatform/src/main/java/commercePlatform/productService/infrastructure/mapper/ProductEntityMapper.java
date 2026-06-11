package commercePlatform.productService.infrastructure.mapper;

import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityMapper {

    public ProductEntity toEntity(Product product){

        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.isActive()
        );
    }

    public Product toDomain(ProductEntity productEntity){

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getStock(),
                productEntity.isActive()
        );
    }
}
