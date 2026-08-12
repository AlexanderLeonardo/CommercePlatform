package commercePlatform.orderService.domain.gateway;

import commercePlatform.orderService.domain.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderGateway implements OrderGateway {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order saveOrder(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Order order = orders.stream()
                .filter(ord -> ord.getId().equals(id))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

}
