package me.nrz.pay_demo.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.nrz.pay_demo.user.dto.request.KaKaoPaymentRequest;
import me.nrz.pay_demo.user.dto.request.TossPaymentRequest;
import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;
import me.nrz.pay_demo.user.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원정보 상세조회
     * @param userId 조회할 사용자 ID (1 이상의 정수)
     * @return 회원의 상세 정보 {@link UserResponse}
     */
    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUserInformation(@PathVariable @Min(1) int userId){
        return ResponseEntity.ok(userService.getUserInformation(userId));
    }

    /**
     * 회원정보 목록조회
     * @param sortBy 목록의 정렬 기준 (이름, 상세 조회수, 프로필 등록 날짜)
     * @param direction 내림차순, 오름차순 결정
     * @param page 조회할 목록의 페이지
     * @param size 페이지당 아이템 수
     * @return 회원목록 정보 {@link UserListResponse}
     */
    @GetMapping
    ResponseEntity<UserListResponse> getUserList(
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(userService.getUserList(sortBy, direction, page, size));
    }

    /**
     * 회원 등록
     * @param registerUserRequest 등록을위해 필요한 회원 정보 {@link RegisterUserRequest}
     * @return 성공시 200 OK 응답
     */
    @PostMapping
    ResponseEntity<Void> registerUser(@RequestBody @Valid RegisterUserRequest registerUserRequest){
        userService.registerUser(registerUserRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 토스 결제포인트 등록
     * @param tossPaymentRequest {@link TossPaymentRequest}
     * @return 성공시 200 OK 응답 {@link JSONObject}
     */
    @PostMapping("/point/toss")
    ResponseEntity<JSONObject> confirmTossPayment(@RequestBody @Valid TossPaymentRequest tossPaymentRequest) throws IOException, ParseException {
        JSONObject jsonObject = userService.confirmTossPayment(tossPaymentRequest);
        return ResponseEntity.ok(jsonObject);
    }

    /**
     * 카카오 결제포인트 등록
     * @param kaKaoPaymentRequest {@link KaKaoPaymentRequest}
     * @return 성공시 200 OK 응답 {@link JSONObject}
     */
    @PostMapping("/point/kakao")
    ResponseEntity<JSONObject> confirmKakaoPayment(@RequestBody @Valid KaKaoPaymentRequest kaKaoPaymentRequest) throws IOException, ParseException {
        log.info("kakao payment success");
        JSONObject jsonObject = userService.confirmKakaoPayment(kaKaoPaymentRequest);
        return ResponseEntity.ok(jsonObject);
    }
}
