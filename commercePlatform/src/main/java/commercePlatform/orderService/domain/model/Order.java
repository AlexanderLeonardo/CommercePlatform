package commercePlatform.orderService.domain.model;

import commercePlatform.orderService.domain.OrderStatus;
import commercePlatform.orderService.exception.*;
import commercePlatform.orderService.domain.model.PaymentStrategy.Payment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Order {

    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private OrderStatus status;
    private BigDecimal total;
    private List<OrderItem> items;  // representa los productos que tiene el pedido

    public Order(Long id, Long userId, String userName, String userEmail, OrderStatus status, BigDecimal total, List<OrderItem> items) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.status = status;
        this.total = total;
        this.items = items;
    }

    public Order(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addOrderItem(OrderItem orderItem){
        // Primero verificar que el estado de la orden sea "CREATED" para agregar un nuevo producto al pedido
        verifyOrderStateForAddOrderItem();
        items.add(orderItem);
        addOrderItemSubtotalInOrderTotal(orderItem.calculateSubtotal());
    }

    private void verifyOrderStateForAddOrderItem() {
        if(!status.equals(OrderStatus.CREATED)){
            throw new CannotAddOrderItemException(status.toString());
        }
    }

    public void addOrderItemSubtotalInOrderTotal(BigDecimal orderItemSubtotal){
        updateOrderTotalWithoutZerosFromDecimals(total.add(orderItemSubtotal));
    }

    public int quantityOfProducts(){
        return this.items.size();
    }

    public void confirmOrder(){
        // Primero verificar que el pedido no esté vacío. Si está vacío, arrojar una excepción
        // del tipo "EmptyOrderException"
        verifyOrderNotEmpty();
        //updateOrderTotalWithoutZerosFromDecimals(paymentMethod.payWithADiscountApplied(total));
        status = OrderStatus.CONFIRMED;
    }

    private void verifyOrderNotEmpty(){
        if(items.isEmpty()){
            throw new EmptyOrderException();
        }
    }

    public Optional<OrderItem> findOrderItemById(Long id) {
        OrderItem orderItem = items.stream()
                .filter(oi -> oi.getId().equals(id))
                .findFirst()
                .orElse(null);
        return Optional.ofNullable(orderItem);
    }

    public void modifyOrderItemWithId(Long idOrderItem, Integer newQuantity) {
        // Por el momento, solo se puede modificar la cantidad del pedido de determinado producto
        // No se puede modificar un pedido que esté confirmado o cancelado.
        verifyOrderStateForModify();
        if(findOrderItemById(idOrderItem).isPresent()){
            OrderItem updateOrderItem = findOrderItemById(idOrderItem).get();
            updateQuantityOrderItem(updateOrderItem, newQuantity);
            this.total = calculateTotal();
        }
    }

    public void updateOrderTotalWithoutZerosFromDecimals(BigDecimal updateTotal){
        this.total = new BigDecimal(updateTotal.stripTrailingZeros().toPlainString());
    }

    private void updateQuantityOrderItem(OrderItem updateOrderItem, Integer newQuantity) {
        updateOrderItem.setQuantity(newQuantity);
    }

    public BigDecimal calculateTotal() {
        BigDecimal total = items.stream()
                            .map(OrderItem::calculateSubtotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new BigDecimal(total.stripTrailingZeros().toPlainString());
    }

    private void verifyOrderStateForModify(){
        if(!status.equals(OrderStatus.CREATED)){
            throw new CannotModifyOrderException(status.toString());
        }
    }

    public void cancelOrder(){
        /*
         IMPORTANTE: no se puede cancelar un pedido que está confirmado.
                     Solamente se puede cancelar cuando está creado.
        */
        verifyOrderStateForCancel();
        status = OrderStatus.CANCELLED;
    }

    private void verifyOrderStateForCancel(){
        if(!status.equals(OrderStatus.CREATED)){
            throw new CannotCancelOrderException(status.toString());
        }
    }

    public void updateOrderTotal(){
        total = calculateTotal();
    }
}
