package ed.lab.ed1labo04.model;

import java.util.List;

public class CreateCartRequest {
    private List<CartItemRequest> cartItems;

    // Getters y setters
    public List<CartItemRequest> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItemRequest> cartItems) { this.cartItems = cartItems; }
}
