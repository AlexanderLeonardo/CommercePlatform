package commercePlatform.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Commerce Platform API",
                version = "1.0",
                description = "API REST para la gestión de productos, clientes y pedidos"
        )
)
public class OpenApiConfig {
}
