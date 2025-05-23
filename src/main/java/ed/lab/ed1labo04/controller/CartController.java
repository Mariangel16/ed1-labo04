package ed.lab.ed1labo04.controller;

import ed.lab.ed1labo04.entity.CartEntity;
import ed.lab.ed1labo04.model.CreateCartRequest;
import ed.lab.ed1labo04.model.CartResponse;
import ed.lab.ed1labo04.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CreateCartRequest createCartRequest) {
        try {
            CartEntity cart = cartService.createCart(createCartRequest);
            return new ResponseEntity<>(new CartResponse(cart), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long id) {
        return cartService.getCartById(id)
                .map(cart -> new ResponseEntity<>(new CartResponse(cart), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
