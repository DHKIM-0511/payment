package me.nrz.pay_demo.coupon.dto.response;

import lombok.Getter;

@Getter
public class CouponApplyResponse {
    private final int finalPrice;
    private final int discountPrice;
    private final CouponResponse couponResponse;
    public CouponApplyResponse(int finalPrice, int discountPrice, CouponResponse couponResponse){
        this.finalPrice = finalPrice;
        this.discountPrice = discountPrice;
        this.couponResponse = couponResponse;
    }
}
