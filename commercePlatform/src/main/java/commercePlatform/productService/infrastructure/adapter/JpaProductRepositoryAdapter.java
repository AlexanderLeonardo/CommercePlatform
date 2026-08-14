package commercePlatform.productService.infrastructure.adapter;

import commercePlatform.productService.domain.gateway.InventoryGateway;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.exception.ProductNotFoundException;
import commercePlatform.productService.infrastructure.entity.ProductEntity;
import commercePlatform.productService.infrastructure.mapper.ProductEntityMapper;
import commercePlatform.productService.infrastructure.repository.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepositoryAdapter implements ProductGateway, InventoryGateway {

    private final JpaProductRepository repository;
    private final ProductEntityMapper mapper;

    public JpaProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductEntityMapper mapper) {
        this.repository = jpaProductRepository;
        this.mapper = mapper;
    }


    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = mapper.toEntity(product);
        repository.save(productEntity);
        return mapper.toDomain(productEntity);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void reserveStock(Long id, int quantity) {
        Product product = findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        product.decreaseStock(quantity);
        this.saveProduct(product);
    }
}
