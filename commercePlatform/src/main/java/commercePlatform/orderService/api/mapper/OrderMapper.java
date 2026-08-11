package commercePlatform.orderService.api.mapper;

import commercePlatform.orderService.api.dto.request.CreateOrderRequest;
import commercePlatform.orderService.api.dto.response.OrderResponse;
import commercePlatform.orderService.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public Order toDomain(CreateOrderRequest request){
        Order order = new Order();
        order.setUserId(request.userId());
        order.setItems(request.items().stream()
                .map(orderItemMapper::toDomain)
                .toList());
        return order;
    }

    public OrderResponse toResponse(Order order){
        return new OrderResponse(order.getId(),
                order.getUserName(),
                order.getUserEmail(),
                order.getStatus(),
                order.getTotal(),
                order.getItems().stream()
                        .map(orderItemMapper::toResponse)
                        .toList());
    }

}
