package commercePlatform.orderService.api.mapper;

import commercePlatform.orderService.api.dto.request.CreateOrderRequest;
import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.api.dto.response.OrderResponse;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public Order toDomain(CreateOrderRequest request){

        List<OrderItem> items = new ArrayList<>();
        for(OrderItemRequest itemRequest: request.items()){
            items.add(orderItemMapper.toDomain(itemRequest));
        }

        Order order = new Order();
        order.setUserId(request.userId());
        order.setItems(items);
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
