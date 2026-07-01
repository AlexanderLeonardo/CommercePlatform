package commercePlatform.userService.api.controller;

import commercePlatform.userService.api.dto.request.UserRequest;
import commercePlatform.userService.api.dto.response.UserResponse;
import commercePlatform.userService.api.mapper.UserMapper;
import commercePlatform.userService.domain.model.User;
import commercePlatform.userService.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        User user = mapper.toDomain(userRequest);
        User saved = service.createUser(user);
        return mapper.toResponse(saved);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        User userFindById = service.getUserById(id);
        return mapper.toResponse(userFindById);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User user = mapper.toDomain(userRequest);
        User updated = service.updateUser(id, user);
        return mapper.toResponse(updated);
    }

    @SuppressWarnings("NullableProblems")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
