// model/CartResponse.java
package ed.lab.ed1labo04.model;

import ed.lab.ed1labo04.entity.CartEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {
    private Long id;
    private List<CartItemResponse> cartItems;
    private double totalPrice;

    public CartResponse(CartEntity entity) {
        this.id = entity.getId();
        this.totalPrice = entity.getTotalPrice();
        this.cartItems = entity.getCartItems().stream()
                .map(CartItemResponse::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() { return id; }
    public List<CartItemResponse> getCartItems() { return cartItems; }
    public double getTotalPrice() { return totalPrice; }
}
