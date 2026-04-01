package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.PaymentRequest;
import com.ecommerce.backend.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout(@Valid @RequestBody PaymentRequest request,
                                                        Authentication authentication) {
        String url = paymentService.checkout(authentication.getName(), request);
        return ResponseEntity.ok(Map.of("checkoutUrl", url));
    }
}
