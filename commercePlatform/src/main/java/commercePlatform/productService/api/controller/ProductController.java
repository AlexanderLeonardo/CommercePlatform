package commercePlatform.productService.api.controller;


import commercePlatform.productService.api.dto.request.ProductRequest;
import commercePlatform.productService.api.dto.response.ProductResponse;
import commercePlatform.productService.api.mapper.ProductMapper;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.domain.service.ProductService;
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

    // IMPLEMENTAR UPDATE DE PRODUCTO!!
}
