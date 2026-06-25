package commercePlatform.productService.api.validator;

import commercePlatform.productService.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validate(Product product){
        product.validationPrice(product.getPrice());
        product.validationStock(product.getStock());
    }
}
