package commercePlatform.orderService.api.controller;

import commercePlatform.orderService.api.dto.request.ConfirmOrderRequest;
import commercePlatform.orderService.api.dto.request.CreateOrderRequest;
import commercePlatform.orderService.api.dto.request.OrderItemRequest;
import commercePlatform.orderService.api.dto.response.OrderResponse;
import commercePlatform.orderService.api.mapper.OrderMapper;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.service.AddItemUseCase;
import commercePlatform.orderService.service.ConfirmOrderUseCase;
import commercePlatform.orderService.service.CreateOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final AddItemUseCase addItemUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final OrderMapper mapper;

    public OrderController(CreateOrderUseCase createOrderUseCase, AddItemUseCase addItemUseCase, ConfirmOrderUseCase confirmOrderUseCase, OrderMapper mapper) {
        this.createOrderUseCase = createOrderUseCase;
        this.addItemUseCase = addItemUseCase;
        this.confirmOrderUseCase = confirmOrderUseCase;
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

    @SuppressWarnings("NullableProblems")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
        Optional<Order> orderFindById = createOrderUseCase.getOrderById(id);
        return orderFindById.map( order -> ResponseEntity.ok(mapper.toResponse(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("NullableProblems")
    @PostMapping("/{id}/items")
    public ResponseEntity<OrderResponse> addOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest request){
        Optional<Order> orderFindById = createOrderUseCase.getOrderById(id);
        return orderFindById.map(order -> ResponseEntity.ok
                                                (mapper.toResponse(addItemUseCase.addOrderItem(order, request))))
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @SuppressWarnings("NullableProblems")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        createOrderUseCase.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @SuppressWarnings("NullableProblems")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable Long id, @RequestBody ConfirmOrderRequest request){
        Optional<Order> orderFindById = createOrderUseCase.getOrderById(id);
        return orderFindById.map(order -> ResponseEntity.ok
                                                (mapper.toResponse(confirmOrderUseCase.confirmOrder(order, request))))
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
