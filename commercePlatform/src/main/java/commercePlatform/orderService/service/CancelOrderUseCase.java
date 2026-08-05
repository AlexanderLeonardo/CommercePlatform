package commercePlatform.orderService.service;

import commercePlatform.orderService.domain.gateway.OrderGateway;

public class CancelOrderUseCase {

    private final OrderGateway orderGateway;

    public CancelOrderUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

}
