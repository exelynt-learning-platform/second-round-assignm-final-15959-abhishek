package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;

public record OrderRequest(@NotBlank String shippingAddress) {

	public String shippingAddress() {
		return shippingAddress;
	}
}
