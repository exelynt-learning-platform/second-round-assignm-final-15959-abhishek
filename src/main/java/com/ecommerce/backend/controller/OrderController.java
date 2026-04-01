package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.OrderRequest;
import com.ecommerce.backend.entity.OrderEntity;
import com.ecommerce.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderEntity> create(@Valid @RequestBody OrderRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrderFromCart(authentication.getName(), request));
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> list(Authentication authentication) {
        return ResponseEntity.ok(orderService.getMyOrders(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> one(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.ok(orderService.getMyOrder(authentication.getName(), id));
    }
}
