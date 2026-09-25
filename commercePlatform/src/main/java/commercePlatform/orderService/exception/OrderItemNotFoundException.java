package commercePlatform.orderService.exception;

import commercePlatform.exceptions.ResourceNotFoundException;

public class OrderItemNotFoundException extends ResourceNotFoundException {

    public OrderItemNotFoundException(Long idOrderItem) {
        super("Not found a order item with id: " + idOrderItem);
    }
}
