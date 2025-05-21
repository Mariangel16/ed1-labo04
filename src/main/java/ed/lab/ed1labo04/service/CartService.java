package ed.lab.ed1labo04.service;

import ed.lab.ed1labo04.entity.CartEntity;
import ed.lab.ed1labo04.entity.CartItemEntity;
import ed.lab.ed1labo04.entity.ProductEntity;
import ed.lab.ed1labo04.model.CartItemRequest;
import ed.lab.ed1labo04.model.CreateCartRequest;
import ed.lab.ed1labo04.repository.CartRepository;
import ed.lab.ed1labo04.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartEntity createCart(CreateCartRequest createCartRequest) {
        CartEntity cart = new CartEntity();
        double totalPrice = 0;
        var cartItemsList = new ArrayList<CartItemEntity>();

        for (CartItemRequest itemReq : createCartRequest.getCartItems()) {
            Optional<ProductEntity> productOpt = productRepository.findById(itemReq.getProductId());

            if (productOpt.isEmpty()) {
                throw new IllegalArgumentException("Product with ID " + itemReq.getProductId() + " does not exist.");
            }

            ProductEntity product = productOpt.get();

            if (itemReq.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }

            if (product.getQuantity() < itemReq.getQuantity()) {
                throw new IllegalArgumentException("Insufficient inventory for product ID " + itemReq.getProductId());
            }

            // Restar la cantidad del inventario del producto
            product.setQuantity(product.getQuantity() - itemReq.getQuantity());
            productRepository.save(product);

            // Crear CartItemEntity
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setProduct(product);
            cartItem.setQuantity(itemReq.getQuantity());
            cartItemsList.add(cartItem);

            // Calcular el precio total
            totalPrice += product.getPrice() * itemReq.getQuantity();
        }

        cart.setCartItems(cartItemsList);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Optional<CartEntity> getCartById(Long id) {
        return cartRepository.findById(id);
    }
}
