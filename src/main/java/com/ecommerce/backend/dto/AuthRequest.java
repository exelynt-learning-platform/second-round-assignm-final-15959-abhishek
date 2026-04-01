package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank String username,
        @NotBlank String password
) {

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}
}
