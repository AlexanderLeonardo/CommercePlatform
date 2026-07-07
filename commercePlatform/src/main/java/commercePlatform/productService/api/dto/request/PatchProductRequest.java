package commercePlatform.productService.api.dto.request;

import java.math.BigDecimal;

public class PatchProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Boolean active;

    public PatchProductRequest(String name, String description, BigDecimal price, Integer stock, Boolean active) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
