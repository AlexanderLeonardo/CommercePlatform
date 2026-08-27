package commercePlatform.orderService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record OrderItemRequest(

     @Schema(description = "ID del producto que el usuario agregó al pedido", example = "5")
     Long productId,
     @Schema(description = "Cantidad solicitada por el usuario", example = "3")
     Integer quantity
) {}
