package commercePlatform.orderService.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long idOrder) {
        super("Not found a order with id: " + idOrder);
    }
}
