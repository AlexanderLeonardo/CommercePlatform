package commercePlatform.productService.api.dto.request;

public class DecreaseStockProductRequest {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DecreaseStockProductRequest(int quantity) {
        this.quantity = quantity;
    }
}
