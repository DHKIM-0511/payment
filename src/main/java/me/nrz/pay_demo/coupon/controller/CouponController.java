package me.nrz.pay_demo.coupon.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.nrz.pay_demo.coupon.dto.request.CouponApplyRequest;
import me.nrz.pay_demo.coupon.dto.request.CouponRequest;
import me.nrz.pay_demo.coupon.dto.response.CouponApplyResponse;
import me.nrz.pay_demo.coupon.dto.response.CouponResponse;
import me.nrz.pay_demo.coupon.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    /**
     * 사용자의 쿠폰 목록 조회
     * @param userId 사용자 Id (1 이상의 정수)
     * @return 쿠폰 목록 {@link CouponResponse}
     */
    @GetMapping("/{userId}")
    ResponseEntity<List<CouponResponse>> getCouponListResponse(@PathVariable int userId){
        return ResponseEntity.ok(couponService.getCouponListResponse(userId));
    }

    /**
     * 사용자의 쿠폰 등록
     * @param couponRequest 쿠폰 등록 파라미터 {@link CouponResponse}
     * @return 성공시 200 OK 반환
     */
    @PostMapping
    ResponseEntity<Void> registerCoupon(@RequestBody @Valid CouponRequest couponRequest){
        couponService.registerCoupon(couponRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 쿠폰 적용
     * @param couponApplyRequest 쿠폰 적용 파라미터 {@link CouponApplyRequest}
     * @return 쿠폰 적용 결과 반환 {@link CouponApplyResponse}
     */
    @DeleteMapping
    ResponseEntity<CouponApplyResponse> applyCoupon(@RequestBody @Valid CouponApplyRequest couponApplyRequest){
        return ResponseEntity.ok(couponService.applyCoupon(couponApplyRequest));
    }
}
