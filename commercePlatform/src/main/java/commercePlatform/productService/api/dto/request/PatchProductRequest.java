package commercePlatform.productService.api.dto.request;

import java.math.BigDecimal;

public record PatchProductRequest(

     String name,
     String description,
     BigDecimal price,
     Integer stock,
     Boolean active
) {}
