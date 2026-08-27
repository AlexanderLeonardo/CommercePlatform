package commercePlatform.orderService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateOrderRequest(

    @Schema(description = "ID del usuario que realiza el pedido", example = "1")
    Long userId,
    @Schema(description = "Listado de productos que el usuario agregó al pedido")
    List<OrderItemRequest> items
) {}
