package com.ecommerce.backend.dto;

public record AuthResponse(String token) {

	public String token() {
		return token;
	}
}
