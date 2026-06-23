package commercePlatform.productService.api.dto.request;

public class UpdateStockProductRequest {

    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public UpdateStockProductRequest(int stock) {
        this.stock = stock;
    }
}
