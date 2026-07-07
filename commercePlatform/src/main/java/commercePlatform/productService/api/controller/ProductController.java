package commercePlatform.productService.api.controller;

import commercePlatform.productService.api.dto.request.DecreaseStockProductRequest;
import commercePlatform.productService.api.dto.request.PatchProductRequest;
import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.api.dto.request.UpdateStockProductRequest;
import commercePlatform.productService.api.dto.response.ProductResponse;
import commercePlatform.productService.api.mapper.ProductMapper;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper;

    //Inyección por constructor
    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Crea un producto nuevo")
    @ApiResponse(responseCode = "200", description = "Producto creado")
    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        Product product = mapper.toDomain(productRequest);
        Product saved = service.createProduct(product);
        return mapper.toResponse(saved);
    }

    @Operation(summary = "Obtiene todos los productos del sistema")
    @ApiResponse(responseCode = "200", description = "Productos del sistema")
    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @Operation(
            summary = "Obtiene un producto por ID",
            description = "Devuelve la información completa de un producto existente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(product ->
                ResponseEntity.ok(mapper.toResponse(product)))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Actualiza los datos de un producto por ID",
            description = "Devuelve la información actualizada de un producto existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y actualizado totalmente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.updateProduct(oldProduct, productRequest))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Actualiza de forma parcial, no total, los datos de un producto por ID",
            description = "Devuelve la información actualizada parcialmente de un producto existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> partialUpdate(@PathVariable Long id, @RequestBody PatchProductRequest patchProductRequest){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.partialUpdateProduct(oldProduct, patchProductRequest))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifica el stock de un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y con stock modificado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PatchMapping("/{id}/stock/set")
    public ResponseEntity<ProductResponse> modifyStock(@PathVariable Long id, @RequestBody UpdateStockProductRequest updateStockRequest){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.modifyStock(oldProduct, updateStockRequest.getStock()))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Restar stock a un producto por ID",
            description = "Le resta stock a un producto existente con una cantidad especificada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y con stock restado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PatchMapping("/{id}/stock/decrease")
    public ResponseEntity<ProductResponse> decreaseStock(@PathVariable Long id, @RequestBody DecreaseStockProductRequest decreaseStockRequest){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.decreaseStock(oldProduct, decreaseStockRequest.getQuantity()))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Desactiva un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y desactivado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PatchMapping("{id}/deactivate")
    public ResponseEntity<ProductResponse> deactivate(@PathVariable Long id){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.deactivate(oldProduct))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Activa un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y activado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @PatchMapping("{id}/activate")
    public ResponseEntity<ProductResponse> activate(@PathVariable Long id){
        Optional<Product> productFindById = service.getProductById(id);
        return productFindById.map(oldProduct ->
                ResponseEntity.ok(mapper.toResponse(service.activate(oldProduct))))
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un producto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado y eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @SuppressWarnings("NullableProblems")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
