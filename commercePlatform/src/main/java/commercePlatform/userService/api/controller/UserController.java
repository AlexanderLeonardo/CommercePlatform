package commercePlatform.userService.api.controller;

import commercePlatform.userService.api.dto.request.UserRequest;
import commercePlatform.userService.api.dto.response.UserResponse;
import commercePlatform.userService.api.mapper.UserMapper;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Crea un usuario nuevo")
    @ApiResponse(responseCode = "200", description = "Usuario creado")
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        User user = mapper.toDomain(userRequest);
        User saved = service.createUser(user);
        return mapper.toResponse(saved);
    }

    @Operation(summary = "Obtiene todos los usuarios del sistema")
    @ApiResponse(responseCode = "200", description = "Usuarios del sistema")
    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @Operation(
            summary = "Obtiene un usuario por ID",
            description = "Devuelve la información completa de un usuario existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        Optional<User> userFindByID = service.getUserById(id);
        return userFindByID.map(user ->
             ResponseEntity.ok(mapper.toResponse(user)))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Actualiza los datos de un usuario por ID",
            description = "Devuelve la información actualizada de un usuario existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado y actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        Optional<User> userFindByID = service.getUserById(id);
        return userFindByID.map(oldUser ->
             ResponseEntity.ok(mapper.toResponse(service.updateUser(oldUser, userRequest))))
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un usuario existente")
    @ApiResponse(responseCode = "204", description = "Solicitud procesada. Usuario eliminado")
    @SuppressWarnings("NullableProblems")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
