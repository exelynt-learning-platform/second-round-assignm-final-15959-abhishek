package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.CartItemRequest;
import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> add(@Valid @RequestBody CartItemRequest request, Authentication authentication) {
        return ResponseEntity.ok(cartService.addOrUpdateItem(authentication.getName(), request));
    }

    @GetMapping
    public ResponseEntity<Cart> get(Authentication authentication) {
        return ResponseEntity.ok(cartService.getMyCart(authentication.getName()));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId, Authentication authentication) {
        cartService.removeItem(authentication.getName(), productId);
        return ResponseEntity.noContent().build();
    }
}
