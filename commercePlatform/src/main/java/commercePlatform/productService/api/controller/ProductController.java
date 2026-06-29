package commercePlatform.productService.api.controller;


import commercePlatform.productService.api.dto.request.DecreaseStockProductRequest;
import commercePlatform.productService.api.dto.request.PatchProductRequest;
import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.api.dto.request.UpdateStockProductRequest;
import commercePlatform.productService.api.dto.response.ProductResponse;
import commercePlatform.productService.api.mapper.ProductMapper;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.domain.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        Product product = mapper.toDomain(productRequest);
        Product saved = service.createProduct(product);
        return mapper.toResponse(saved);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id){
        Product productFindById = service.getProductById(id);
        return mapper.toResponse(productFindById);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        Product product = mapper.toDomain(productRequest);
        Product updated = service.updateProduct(id, product);
        return mapper.toResponse(updated);
    }

    @PatchMapping("/{id}")
    public ProductResponse partialUpdate(@PathVariable Long id, @RequestBody PatchProductRequest patchProductRequest){
        Product partialUpdate = service.partialUpdateProduct(id, patchProductRequest);
        return mapper.toResponse(partialUpdate);
    }

    @PatchMapping("/{id}/stock/set")
    public ProductResponse modifyStock(@PathVariable Long id, @RequestBody UpdateStockProductRequest updateStockRequest){
        int updatedStock = updateStockRequest.getStock();
        Product updatedProduct = service.modifyStock(id, updatedStock);
        return mapper.toResponse(updatedProduct);
    }

    @PatchMapping("/{id}/stock/decrease")
    public ProductResponse decreaseStock(@PathVariable Long id, @RequestBody DecreaseStockProductRequest decreaseStockRequest){
        int quantityDecrease = decreaseStockRequest.getQuantity();
        Product decreaseStockProduct = service.decreaseStock(id, quantityDecrease);
        return mapper.toResponse(decreaseStockProduct);
    }

    @PatchMapping("{id}/deactivate")
    public ProductResponse deactivate(@PathVariable Long id){
        Product deactivateProduct = service.deactivate(id);
        return mapper.toResponse(deactivateProduct);
    }

    @PatchMapping("{id}/activate")
    public ProductResponse activate(@PathVariable Long id){
        Product activateProduct = service.activate(id);
        return mapper.toResponse(activateProduct);
    }

    @SuppressWarnings("NullableProblems")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
