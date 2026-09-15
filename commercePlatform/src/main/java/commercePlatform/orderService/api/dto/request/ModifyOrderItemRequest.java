package commercePlatform.orderService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ModifyOrderItemRequest(

        @Schema(description = "ID del item del pedido que se quiere modificar", example = "8")
        Long idOrderItem,
        @Schema(description = "Cantidad que se quiere modificar en el item del pedido", example = "5")
        Integer quantity
) {}
