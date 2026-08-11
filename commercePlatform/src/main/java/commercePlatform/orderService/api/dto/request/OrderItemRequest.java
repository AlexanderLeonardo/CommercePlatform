package commercePlatform.orderService.api.dto.request;

public record OrderItemRequest(

     Long productId,
     Integer quantity
) {}
