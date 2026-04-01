package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.OrderRequest;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderFromCartCalculatesTotal() {
        User user = new User();
        user.setUsername("rohit");

        Product p = new Product();
        p.setName("Phone");
        p.setPrice(new BigDecimal("100.00"));
        p.setStockQuantity(10);

        CartItem item = new CartItem();
        item.setProduct(p);
        item.setQuantity(2);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>(List.of(item)));

        when(userRepository.findByUsername("rohit")).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        OrderEntity created = orderService.createOrderFromCart("rohit", new OrderRequest("Pune, India"));
        assertEquals(new BigDecimal("200.00"), created.getTotalPrice());
        assertEquals(1, created.getItems().size());
    }
}
