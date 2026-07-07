package commercePlatform.productService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProductRequest {

    @Schema(description = "Nombre del producto", example = "Notebook Lenovo ThinkPad")
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private boolean active;

    public ProductRequest(String name, String description, BigDecimal price, int stock, boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
