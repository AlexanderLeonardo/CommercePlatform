package commercePlatform.productService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductRequest (

    @Schema(description = "Nombre del producto", example = "Notebook Lenovo ThinkPad")
    String name,
    @Schema(description = "Descripción del producto")
    String description,
    @Schema(description = "Precio del producto")
    BigDecimal price,
    @Schema(description = "Stock disponible del producto")
    int stock,
    @Schema(description = "Flag que determina si un producto está disponible o no")
    boolean active) {}
