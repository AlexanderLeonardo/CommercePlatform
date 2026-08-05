package commercePlatform.productService.domain.gateway;

public interface InventoryGateway {

    void reserveStock(Long id, int quantity);
}
