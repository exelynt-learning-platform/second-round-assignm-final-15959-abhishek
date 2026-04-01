package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.OrderRequest;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.exception.BadRequestException;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderEntity createOrderFromCart(String username, OrderRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setShippingAddress(request.shippingAddress());

        BigDecimal total = BigDecimal.ZERO;
        for (CartItem ci : cart.getItems()) {
            if (ci.getProduct().getStockQuantity() < ci.getQuantity()) {
                throw new BadRequestException("Insufficient stock for product: " + ci.getProduct().getName());
            }

            ci.getProduct().setStockQuantity(ci.getProduct().getStockQuantity() - ci.getQuantity());
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPriceAtPurchase(ci.getProduct().getPrice());
            order.getItems().add(oi);
            total = total.add(ci.getProduct().getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
        }
        order.setTotalPrice(total);

        cart.getItems().clear();
        return orderRepository.save(order);
    }

    public List<OrderEntity> getMyOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return orderRepository.findByUser(user);
    }

    public OrderEntity getMyOrder(String username, Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        if (!order.getUser().getUsername().equals(username)) {
            throw new BadRequestException("You cannot access this order");
        }
        return order;
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }
}
