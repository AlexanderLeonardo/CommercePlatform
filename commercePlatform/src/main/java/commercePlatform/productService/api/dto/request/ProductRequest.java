package commercePlatform.productService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest (

    @NotBlank
    @Schema(description = "Nombre del producto", example = "Notebook Lenovo ThinkPad")
    String name,
    @NotBlank
    @Schema(description = "Descripción del producto", example = "Notebook Lenovo with SDD 256GB")
    String description,
    @NotNull
    @Schema(description = "Precio del producto", example = "1200")
    BigDecimal price,
    @Schema(description = "Stock disponible del producto", example = "25")
    int stock,
    @Schema(description = "Flag que determina si un producto está disponible o no", example = "true")
    boolean active) {}
