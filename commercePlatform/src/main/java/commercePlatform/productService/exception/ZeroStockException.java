package commercePlatform.productService.exception;

public class ZeroStockException extends RuntimeException {
    public ZeroStockException(String nameProduct) {
        super("The product named " + nameProduct + " is out of stock");
    }
}
