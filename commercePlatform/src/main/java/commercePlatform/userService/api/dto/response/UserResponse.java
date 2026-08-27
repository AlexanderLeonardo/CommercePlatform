package commercePlatform.userService.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {

    @Schema(description = "ID del usuario", example = "1")
    private Long id;
    @Schema(description = "Nombre del usuario", example = "Juan Perez")
    private String name;
    @Schema(description = "Email del usuario", example = "juan.perez@gmail.com")
    private String email;
    @Schema(description = "Dirección de residencia del usuario", example = "Avenida Siempre Viva 742")
    private String address;

    public UserResponse(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
