package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CartItemRequest;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getMyCart(String username) {
        User user = getUser(username);
        return cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
    }

    @Transactional
    public Cart addOrUpdateItem(String username, CartItemRequest request) {
        Cart cart = getMyCart(username);
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        CartItem item = cartItemRepository.findByCartAndProduct(cart, product).orElseGet(() -> {
            CartItem ci = new CartItem();
            ci.setCart(cart);
            ci.setProduct(product);
            return ci;
        });
        item.setQuantity(request.quantity());
        cartItemRepository.save(item);
        return cart;
    }

    @Transactional
    public void removeItem(String username, Long productId) {
        Cart cart = getMyCart(username);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        CartItem item = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItemRepository.delete(item);
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
