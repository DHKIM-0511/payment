package me.nrz.pay_demo.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterUserRequest {
    @NotBlank(message = "Name is required.")
    private String name;
}
