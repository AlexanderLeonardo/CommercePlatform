package commercePlatform.orderService.api.controller;

import commercePlatform.orderService.api.dto.request.CreateOrderRequest;
import commercePlatform.orderService.api.dto.response.OrderResponse;
import commercePlatform.orderService.api.mapper.OrderMapper;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.service.CreateOrderUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final OrderMapper mapper;

    public OrderController(CreateOrderUseCase createOrderUseCase, OrderMapper mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request){
        Order order = mapper.toDomain(request);
        Order save = createOrderUseCase.createOrder(order);
        return mapper.toResponse(save);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return createOrderUseCase.getAllOrders();
    }
}
