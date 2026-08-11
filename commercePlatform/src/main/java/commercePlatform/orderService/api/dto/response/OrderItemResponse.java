package commercePlatform.orderService.api.dto.response;

import java.math.BigDecimal;

public class OrderItemResponse {

    private Long id;
    private String productName;
    private BigDecimal productPrice;
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
