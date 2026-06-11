package commercePlatform.testModel.Product;

import commercePlatform.productService.exception.InvalidPriceException;
import commercePlatform.productService.exception.InvalidStockException;
import commercePlatform.productService.domain.gateway.InMemoryProductGateway;
import commercePlatform.productService.domain.gateway.ProductGateway;
import commercePlatform.productService.domain.model.Product;
import commercePlatform.productService.domain.service.CreateProductService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProductTest {

    ProductGateway productGateway = new InMemoryProductGateway();
    CreateProductService createProductService = new CreateProductService(productGateway);

    @Test
    void shouldNotCreateProductWithNegativePrice(){

        assertThrows(InvalidPriceException.class,
                () -> new Product(Long.valueOf(1), "Tablet", "Tablet sorny", BigDecimal.valueOf(-10), 10, true));
    }

    @Test
    void shouldNotCreateProductWithZeroPrice(){
        assertThrows(InvalidPriceException.class,
                () -> new Product(Long.valueOf(2), "Smarthphone", "Smartphone Apple", BigDecimal.valueOf(0), 5, true));
    }

    @Test
    void shouldNotCreateProductWithNegativeStock(){
        assertThrows(InvalidStockException.class,
                () -> new Product(Long.valueOf(3), "Notebook", "Notebook Octopus with Windows 10", BigDecimal.valueOf(500), -3, true));
    }

    @Test
    void shouldCreateProductSuccessfully(){
        Product smartwatch = new Product(Long.valueOf(4), "Smartwatch", "Smartwatch samsung", BigDecimal.valueOf(350), 20, true);
        Product createdProduct = createProductService.createProduct(smartwatch);
        assertEquals("Smartwatch", createdProduct.getName());
    }

    @Test
    void shouldDecreaseStockProduct(){
        Product monitorPC = new Product(Long.valueOf(5), "Monitor PC", "Monitor for PC LG", BigDecimal.valueOf(150), 20, true);
        Product updatedProduct = createProductService.decreaseStockProduct(monitorPC, 5);
        assertEquals(15, updatedProduct.getStock());
    }

    @Test
    void shouldDeactivateProduct(){
        Product headphones = new Product(Long.valueOf(6), "Headphones", "Wireless headphones Phillips", BigDecimal.valueOf(170), 35, true);
        Product updatedProduct = createProductService.deactivateProduct(headphones);
        assertFalse(updatedProduct.isActive());
    }

}
