package commercePlatform.orderService.exception;

import commercePlatform.exceptions.ResourceNotFoundException;

public class OrderNotFoundException extends ResourceNotFoundException {

    public OrderNotFoundException(Long idOrder) {
        super("Not found a order with id: " + idOrder);
    }
}
