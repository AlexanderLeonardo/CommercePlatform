package commercePlatform.productService.infrastructure.adapter;

import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.infrastructure.entity.ProductEntity;
import commercePlatform.productService.infrastructure.mapper.ProductEntityMapper;
import commercePlatform.productService.infrastructure.repository.JpaProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepositoryAdapter implements ProductGateway {

    private final JpaProductRepository repository;
    private final ProductEntityMapper mapper;

    public JpaProductRepositoryAdapter(JpaProductRepository jpaProductRepository, ProductEntityMapper mapper) {
        this.repository = jpaProductRepository;
        this.mapper = mapper;
    }


    @Override
    public Product saveProduct(Product product) {
        ProductEntity entity = mapper.toEntity(product);
        ProductEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
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
}
