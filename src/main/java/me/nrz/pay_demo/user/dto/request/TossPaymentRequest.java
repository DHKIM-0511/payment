package me.nrz.pay_demo.user.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TossPaymentRequest {
    @Min(value = 1, message = "userId must be greater than or equal to 1.")
    private int userId;

    @NotBlank(message = "paymentKey is required.")
    private String paymentKey;

    @NotBlank(message = "orderId is required.")
    private String orderId;

    @NotNull(message = "amount is required.")
    private long amount;
}
