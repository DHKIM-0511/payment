package me.nrz.pay_demo.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int viewCount;
    private int point;
    private LocalDateTime createdAt;

    public User(@NotBlank(message = "Name is required.") String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public void increaseViewCount(){
        this.viewCount++;
    }
}
