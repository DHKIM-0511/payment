package me.nrz.pay_demo.coupon.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import me.nrz.pay_demo.coupon.entity.Coupon;

@Getter
public class CouponResponse {
    private final int id;
    private final String couponName;
    private final int discountRate;
    private final LocalDateTime createdAt;
    private final LocalDateTime expirationAt;

    public CouponResponse(Coupon coupon){
        this.id = coupon.getId();
        this.couponName = coupon.getName();
        this.discountRate = coupon.getDiscountRate();
        this.createdAt = coupon.getCreatedAt();
        this.expirationAt = coupon.getExpirationAt();
    }
}
