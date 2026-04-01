package com.ecommerce.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemRequest(
        @NotNull Long productId,
        @Min(1) Integer quantity
) {

	public Long productId() {
		return productId;
	}

	public Integer quantity() {
		return quantity;
	}
}
