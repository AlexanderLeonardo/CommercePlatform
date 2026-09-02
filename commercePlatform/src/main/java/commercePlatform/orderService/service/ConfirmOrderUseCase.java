package commercePlatform.orderService.service;

import commercePlatform.orderService.api.dto.request.ConfirmOrderRequest;
import commercePlatform.orderService.domain.model.Order;
import commercePlatform.orderService.domain.gateway.OrderGateway;
import commercePlatform.orderService.domain.model.OrderItem;
import commercePlatform.orderService.domain.model.PaymentStrategy.Payment;
import commercePlatform.orderService.factory.PaymentFactory;
import commercePlatform.productService.domain.gateway.InventoryGateway;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ConfirmOrderUseCase {

    private final OrderGateway orderGateway;
    private final InventoryGateway inventoryGateway;
    private final PaymentFactory paymentFactory;
    private final ProductGateway productGateway;

    public ConfirmOrderUseCase(OrderGateway orderGateway, InventoryGateway inventoryGateway, PaymentFactory paymentFactory, ProductGateway productGateway) {
        this.orderGateway = orderGateway;
        this.inventoryGateway = inventoryGateway;
        this.paymentFactory = paymentFactory;
        this.productGateway = productGateway;
    }

    //@Transactional
    public Order confirmOrder(Order order, ConfirmOrderRequest confirmOrderRequest){
        Payment payment = paymentFactory.getPayment(confirmOrderRequest.paymentMethod());
        for(OrderItem item: order.getItems()){
            Product product = productGateway.findById(item.getProductId())
                                            .orElseThrow(() -> new ProductNotFoundException(item.getProductId()));
            product.verifyCurrentStock();
            inventoryGateway.reserveStock(product, item.getQuantity());
        }
        BigDecimal orderTotalWithDiscount = payment.payWithADiscountApplied(order.getTotal());
        order.updateOrderTotalWithoutZerosFromDecimals(orderTotalWithDiscount);
        order.confirmOrder();
        return this.orderGateway.saveOrder(order);
    }

}
