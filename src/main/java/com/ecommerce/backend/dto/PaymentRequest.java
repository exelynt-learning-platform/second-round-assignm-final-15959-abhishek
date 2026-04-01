package com.ecommerce.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(
        @NotNull Long orderId,
        @NotBlank String currency,
        @NotBlank String successUrl,
        @NotBlank String cancelUrl
) {

	public Long orderId() {
		return orderId;
	}

	public String currency() {
		return currency;
	}

	public String successUrl() {
		return successUrl;
	}

	public String cancelUrl() {
		return cancelUrl;
	}
}
