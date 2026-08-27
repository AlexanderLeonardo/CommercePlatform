package commercePlatform.orderService.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class OrderItemResponse {

    @Schema(description = "ID del item del pedido", example = "1")
    private Long id;
    @Schema(description = "Nombre del producto", example = "Headphone")
    private String productName;
    @Schema(description = "Precio del producto", example = "120")
    private BigDecimal productPrice;
    @Schema(description = "Cantidad solicitada por el usuario", example = "2")
    private Integer quantity;

    public OrderItemResponse(Long id, String productName, BigDecimal productPrice, Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
