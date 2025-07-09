package me.nrz.pay_demo.coupon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.nrz.pay_demo.coupon.dto.request.CouponRequest;
import me.nrz.pay_demo.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private int discountRate;

    private LocalDateTime createdAt;

    private LocalDateTime expirationAt;

    public Coupon(User user, CouponRequest couponRequest){
        LocalDateTime now = LocalDateTime.now();
        this.user = user;
        this.name = couponRequest.getName();
        this.discountRate = couponRequest.getDiscountRate();
        this.createdAt = now;
        this.expirationAt = now.plusDays(7);
    }
}
