package me.nrz.pay_demo.coupon.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class CouponApplyRequest {
    @Min(value = 1, message = "userId must be greater than or equal to 1.")
    private int userId;

    @Min(value = 1, message = "couponId must be greater than or equal to 1.")
    private int couponId;

    @Min(value = 1, message = "originalPrice must be greater than or equal to 1.")
    private int originalPrice;
}
