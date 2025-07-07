package me.nrz.pay_demo.user.service;

import jakarta.validation.Valid;
import java.io.IOException;
import me.nrz.pay_demo.user.dto.request.KaKaoPaymentRequest;
import me.nrz.pay_demo.user.dto.request.TossPaymentRequest;
import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public interface UserService {

    UserResponse getUserInformation(int userId);

    UserListResponse getUserList(String sortBy, String direction, int page, int size);

    void registerUser(RegisterUserRequest registerUserRequest);

    JSONObject confirmTossPayment(@Valid TossPaymentRequest payRequest) throws IOException, ParseException;

    JSONObject confirmKakaoPayment(@Valid KaKaoPaymentRequest kaKaoPaymentRequest) throws IOException, ParseException;
}
