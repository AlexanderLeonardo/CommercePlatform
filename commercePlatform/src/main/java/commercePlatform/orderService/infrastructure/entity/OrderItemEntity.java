package commercePlatform.orderService.infrastructure.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;

    protected OrderItemEntity(){

    }

    public OrderItemEntity(Long id, Long productId, String productName, BigDecimal productPrice, Integer quantity) {
        this.id = id;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public BigDecimal calculateSubtotal(){
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String productName(){
        return productName;
    }
}
