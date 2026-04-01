package com.ecommerce.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank String name,
        String description,
        @DecimalMin(value = "0.0", inclusive = false) BigDecimal price,
        @Min(0) Integer stockQuantity,
        String imageUrl
) {

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}

	public BigDecimal price() {
		return price;
	}

	public Integer stockQuantity() {
		return stockQuantity;
	}

	public String imageUrl() {
		return imageUrl;
	}
}
