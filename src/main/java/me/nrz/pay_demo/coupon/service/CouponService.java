package me.nrz.pay_demo.coupon.service;

import jakarta.validation.Valid;
import java.util.List;
import me.nrz.pay_demo.coupon.dto.request.CouponApplyRequest;
import me.nrz.pay_demo.coupon.dto.request.CouponRequest;
import me.nrz.pay_demo.coupon.dto.response.CouponApplyResponse;
import me.nrz.pay_demo.coupon.dto.response.CouponResponse;

public interface CouponService {

    List<CouponResponse> getCouponListResponse(int userId);

    void registerCoupon(@Valid CouponRequest couponRequest);

    CouponApplyResponse applyCoupon(@Valid CouponApplyRequest couponApplyRequest);
}
