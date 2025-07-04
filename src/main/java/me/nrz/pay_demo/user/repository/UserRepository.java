package me.nrz.pay_demo.user.repository;

import me.nrz.pay_demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
