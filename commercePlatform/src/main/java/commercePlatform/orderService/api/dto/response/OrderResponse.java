package commercePlatform.orderService.api.dto.response;

import commercePlatform.orderService.domain.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponse {

    @Schema(description = "ID del pedido", example = "7")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String userName;
    @Schema(description = "Email del usuario", example = "juan.perez@gmail.com")
    private String userEmail;
    @Schema(description = "Estado en el que se encuentra el pedido", example = "CREATED")
    private OrderStatus status;
    @Schema(description = "Costo total acumulado del pedido", example = "1500.50")
    private BigDecimal total;
    @Schema(description = "Listado de productos que el usuario agregó al pedido")
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
