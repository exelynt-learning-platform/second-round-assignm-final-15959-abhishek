package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.RegisterRequest;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerCreatesUserAndCart() {
        when(userRepository.existsByUsername("rohit")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("hashed");
        User saved = new User();
        saved.setId(1L);
        saved.setUsername("rohit");
        saved.setPassword("hashed");
        when(userRepository.save(any(User.class))).thenReturn(saved);

        authService.register(new RegisterRequest("rohit", "pass"));

        verify(userRepository).save(any(User.class));
        verify(cartRepository).save(any());
    }
}
