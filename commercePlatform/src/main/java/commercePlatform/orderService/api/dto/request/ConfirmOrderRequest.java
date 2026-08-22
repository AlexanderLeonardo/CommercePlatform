package commercePlatform.orderService.api.dto.request;

import commercePlatform.orderService.domain.model.PaymentStrategy.PaymentMethod;

public record ConfirmOrderRequest(
        PaymentMethod paymentMethod
) {}
