package me.nrz.pay_demo.coupon.repository;

import java.util.List;
import java.util.Optional;
import me.nrz.pay_demo.coupon.entity.Coupon;
import me.nrz.pay_demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    List<Coupon> findAllByUser(User user);

    Optional<Coupon> findByIdAndUser(int couponId, User user);
}
