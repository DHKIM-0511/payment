package me.nrz.pay_demo.coupon.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CouponRequest {
    @Min(value = 1, message = "userId must be greater than or equal to 1.")
    private int userId;

    @NotBlank(message = "coupon name is required.")
    private String name;

    @Min(value = 1, message = "discountRate must be greater than or equal to 1.")
    private int discountRate;
}
