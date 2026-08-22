package commercePlatform.orderService.service;

import commercePlatform.orderService.api.dto.request.ConfirmOrderRequest;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.PaymentStrategy.Payment;
import commercePlatform.orderService.exception.OrderNotFoundException;
import commercePlatform.orderService.factory.PaymentFactory;
import commercePlatform.productService.domain.gateway.InventoryGateway;

import java.math.BigDecimal;

public class ConfirmOrderUseCase {

    private final OrderGateway orderGateway;
    private final InventoryGateway inventoryGateway;
    private final PaymentFactory paymentFactory;

    public ConfirmOrderUseCase(OrderGateway orderGateway, InventoryGateway inventoryGateway, PaymentFactory paymentFactory) {
        this.orderGateway = orderGateway;
        this.inventoryGateway = inventoryGateway;
        this.paymentFactory = paymentFactory;
    }

    public void confirmOrder(Long idOrder, ConfirmOrderRequest confirmOrderRequest){
        Payment payment = paymentFactory.getPayment(confirmOrderRequest.paymentMethod());
        Order order = orderGateway.findById(idOrder).orElseThrow(() -> new OrderNotFoundException(idOrder));
        for(OrderItem item: order.getItems()){
            inventoryGateway.reserveStock(item.getProductId(), item.getQuantity());
        }
        BigDecimal orderTotalWithDiscount = payment.payWithADiscountApplied(order.getTotal());
        order.updateOrderTotalWithoutZerosFromDecimals(orderTotalWithDiscount);
        order.confirmOrder();
        orderGateway.saveOrder(order);
    }

}
