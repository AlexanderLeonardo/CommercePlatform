package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.exception.OrderNotFoundException;
import commercePlatform.productService.domain.gateway.InventoryGateway;

public class ConfirmOrderUseCase {

    private final OrderGateway orderGateway;
    private final InventoryGateway inventoryGateway;

    public ConfirmOrderUseCase(OrderGateway orderGateway, InventoryGateway inventoryGateway) {
        this.orderGateway = orderGateway;
        this.inventoryGateway = inventoryGateway;
    }

    public void confirmOrder(Long idOrder){
        Order order = orderGateway.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        for(OrderItem item: order.getItems()){
            inventoryGateway.reserveStock(item.getProductId(), item.getQuantity());
        }
        order.confirmOrder();
        orderGateway.saveOrder(order);
    }

}
