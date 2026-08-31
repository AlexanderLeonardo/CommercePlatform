package commercePlatform.userService.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String name;
    @NotBlank
    @Schema(description = "Email del usuario", example = "juan.perez@gmail.com")
    private String email;
    @NotBlank
    @Schema(description = "Dirección de residencia del usuario", example = "Avenida Siempre Viva 742")
    private String address;

    public UserRequest(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
