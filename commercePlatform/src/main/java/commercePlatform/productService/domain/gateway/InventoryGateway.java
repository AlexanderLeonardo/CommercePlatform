package commercePlatform.productService.domain.gateway;

import commercePlatform.productService.domain.model.Product;

public interface InventoryGateway {

    void reserveStock(Product product, int quantity);
}
