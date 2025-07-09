package me.nrz.pay_demo.coupon.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.nrz.pay_demo.coupon.dto.request.CouponApplyRequest;
import me.nrz.pay_demo.coupon.dto.request.CouponRequest;
import me.nrz.pay_demo.coupon.dto.response.CouponApplyResponse;
import me.nrz.pay_demo.coupon.dto.response.CouponResponse;
import me.nrz.pay_demo.coupon.entity.Coupon;
import me.nrz.pay_demo.coupon.repository.CouponRepository;
import me.nrz.pay_demo.user.entity.User;
import me.nrz.pay_demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;

    @Override
    public List<CouponResponse> getCouponListResponse(int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        List<Coupon> coupons = couponRepository.findAllByUser(user);

        return coupons.stream().map(CouponResponse::new).collect(
            Collectors.toList());
    }

    @Override
    public void registerCoupon(CouponRequest couponRequest) {
        User user = userRepository.findById(couponRequest.getUserId())
            .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        couponRepository.save(new Coupon(user, couponRequest));
    }

    @Override
    public CouponApplyResponse applyCoupon(CouponApplyRequest couponApplyRequest) {
        User user = userRepository.findById(couponApplyRequest.getUserId())
            .orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        Coupon coupon = couponRepository.findByIdAndUser(couponApplyRequest.getCouponId(), user)
            .orElseThrow(()-> new IllegalArgumentException("Coupon Not Found"));

        int originalPrice = couponApplyRequest.getOriginalPrice();
        int discountPrice = (int) Math.ceil(originalPrice * (coupon.getDiscountRate() / 100.0));
        couponRepository.delete(coupon);

        if(discountPrice > 5000){
            return new CouponApplyResponse(originalPrice - 5000, 5000, new CouponResponse(coupon));
        }
        return new CouponApplyResponse(originalPrice - discountPrice, discountPrice, new CouponResponse(coupon));
    }
}
