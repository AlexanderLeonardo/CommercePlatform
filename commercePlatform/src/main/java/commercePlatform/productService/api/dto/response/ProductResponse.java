package commercePlatform.productService.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ProductResponse {

    @Schema(description = "ID del producto", example = "4")
    private Long id;
    @Schema(description = "Nombre del producto", example = "Monitor LG")
    private String name;
    @Schema(description = "Descripción del producto", example = "Monitor LG 15 pulgadas con HDMI")
    private String description;
    @Schema(description = "Precio del producto", example = "450")
    private BigDecimal price;
    @Schema(description = "Stock disponible del producto", example = "30")
    private int stock;
    @Schema(description = "Flag que determina si un producto está disponible o no", example = "true")
    private boolean active;

    public ProductResponse(Long id, String name, String description, BigDecimal price, int stock, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
