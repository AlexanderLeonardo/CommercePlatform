package commercePlatform.orderService.api.dto.response;

import commercePlatform.orderService.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    private Long id;
    private String userName;
    private String userEmail;
    private OrderStatus status;
    private BigDecimal total;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id, String userName, String userEmail, OrderStatus status, BigDecimal total, List<OrderItemResponse> items) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.status = status;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }
}
