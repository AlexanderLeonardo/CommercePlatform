package commercePlatform.productService.domain.model;

import commercePlatform.productService.exception.InsufficientStockException;
import commercePlatform.productService.exception.InvalidPriceException;
import commercePlatform.productService.exception.InvalidStockException;

import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private boolean active;

    public Product(Long id, String name, String description, BigDecimal price, int stock, boolean active) {
        validationPrice(price);
        validationStock(stock);
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.active = active;
    }

    public Product(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void decreaseStock(int quantity){
        if(quantity > stock){ // First check new stock
            throw new InsufficientStockException();
        }
        stock -= quantity;
    }

    public void deactivate(){
        active = false;
    }

    public void activate(){
        active = true;
    }

    public void validationPrice(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidPriceException();
        }
    }

    public void validationStock(int stock){
        if(stock < 0){
            throw new InvalidStockException();
        }
    }
}
