package commercePlatform.testModel.Product;

import commercePlatform.productService.exception.InsufficientStockException;
import commercePlatform.productService.exception.InvalidPriceException;
import commercePlatform.productService.exception.InvalidStockException;
import commercePlatform.productService.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void shouldNotCreateProductWithNegativePrice(){

        assertThrows(InvalidPriceException.class,
                () -> new Product(1L, "Tablet", "Tablet sorny", BigDecimal.valueOf(-10), 10, true));
    }

    @Test
    void shouldNotCreateProductWithZeroPrice(){
        assertThrows(InvalidPriceException.class,
                () -> new Product(2L, "Smarthphone", "Smartphone Apple", BigDecimal.valueOf(0), 5, true));
    }

    @Test
    void shouldNotCreateProductWithNegativeStock(){
        assertThrows(InvalidStockException.class,
                () -> new Product(3L, "Notebook", "Notebook Octopus with Windows 10", BigDecimal.valueOf(500), -3, true));
    }

    @Test
    void shouldCreateProductSuccessfully(){
        Product smartwatch = new Product(4L, "Smartwatch", "Smartwatch samsung", BigDecimal.valueOf(350), 20, true);
        assertEquals("Smartwatch", smartwatch.getName());
    }

    @Test
    void shouldDecreaseStockProduct(){
        Product monitorPC = new Product(5L, "Monitor PC", "Monitor for PC LG", BigDecimal.valueOf(150), 20, true);
        monitorPC.decreaseStock(5);
        assertEquals(15, monitorPC.getStock());
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient(){
        Product smartTv = new Product(6L, "SmartTv", "Tv Smart 63p Ultra HD 4K", BigDecimal.valueOf(680), 7, true);
        assertThrows(InsufficientStockException.class,
                ()-> smartTv.decreaseStock(9));
    }

    @Test
    void shouldDeactivateProduct(){
        Product headphones = new Product(7L, "Headphones", "Wireless headphones Phillips", BigDecimal.valueOf(170), 35, true);
        headphones.deactivate();
        assertFalse(headphones.isActive());
    }

}
