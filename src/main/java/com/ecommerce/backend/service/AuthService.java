package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.AuthRequest;
import com.ecommerce.backend.dto.AuthResponse;
import com.ecommerce.backend.dto.RegisterRequest;
import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.entity.enums.Role;
import com.ecommerce.backend.exception.BadRequestException;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        User saved = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(saved);
        cartRepository.save(cart);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));
        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build()
        );
        return new AuthResponse(token);
    }
}
