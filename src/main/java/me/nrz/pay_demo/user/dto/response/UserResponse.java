package me.nrz.pay_demo.user.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import me.nrz.pay_demo.user.entity.User;

@Getter
public class UserResponse {
    private final int id;
    private final String name;
    private final int viewCount;
    private final LocalDateTime createdAt;

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.viewCount = user.getViewCount();
        this.createdAt = user.getCreatedAt();
    }
}
