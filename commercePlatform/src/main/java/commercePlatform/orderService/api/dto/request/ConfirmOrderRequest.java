package commercePlatform.orderService.api.dto.request;

import commercePlatform.orderService.domain.model.PaymentStrategy.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;

public record ConfirmOrderRequest(
        @Schema(description = "Método o forma de pago que realizará el usuario", example = "CREDIT_CARD")
        PaymentMethod paymentMethod
) {}
