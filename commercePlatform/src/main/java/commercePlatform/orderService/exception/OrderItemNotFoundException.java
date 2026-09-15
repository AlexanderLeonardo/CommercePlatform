package commercePlatform.orderService.exception;

public class OrderItemNotFoundException extends RuntimeException {

    public OrderItemNotFoundException(Long idOrderItem) {
        super("Not found a order item with id: " + idOrderItem);
    }
}
