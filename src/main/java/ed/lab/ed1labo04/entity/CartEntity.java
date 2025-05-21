package ed.lab.ed1labo04.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")  // FK en CartItemEntity
    private List<CartItemEntity> cartItems;

    private double totalPrice;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<CartItemEntity> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItemEntity> cartItems) { this.cartItems = cartItems; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
