// model/CartItemResponse.java
package ed.lab.ed1labo04.model;

import ed.lab.ed1labo04.entity.CartItemEntity;

public class CartItemResponse {
    private Long productId;
    private String name;
    private double price;
    private int quantity;

    public CartItemResponse(CartItemEntity item) {
        this.productId = item.getProduct().getId();
        this.name = item.getProduct().getName();
        this.price = item.getProduct().getPrice();
        this.quantity = item.getQuantity();
    }

    // Getters
    public Long getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
