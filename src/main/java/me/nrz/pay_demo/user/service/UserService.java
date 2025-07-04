package me.nrz.pay_demo.user.service;

import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserInformation(int userId);

    UserListResponse getUserList(String sortBy, String direction, int page, int size);

    void registerUser(RegisterUserRequest registerUserRequest);
}
