package commercePlatform.orderService.api.dto.request;

import java.util.List;

public record CreateOrderRequest(

    Long userId,
    List<OrderItemRequest> items
) {}
