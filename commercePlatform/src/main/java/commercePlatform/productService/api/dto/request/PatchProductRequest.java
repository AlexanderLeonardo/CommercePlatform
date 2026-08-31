package commercePlatform.productService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PatchProductRequest(

     @NotBlank
     @Schema(description = "Nombre del producto", example = "Silla Gammer")
     String name,
     @NotBlank
     @Schema(description = "Descripción del producto", example = "Silla Gammer de 1.50 mtrs")
     String description,
     @Schema(description = "Precio del producto", example = "380")
     BigDecimal price,
     @Schema(description = "Stock disponible del producto", example = "10")
     Integer stock,
     @Schema(description = "Flag que determina si un producto está disponible o no", example = "false")
     Boolean active
) {}
