package me.nrz.pay_demo.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;
import me.nrz.pay_demo.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> getUserInformation(@PathVariable int userId){
        return ResponseEntity.ok(userService.getUserInformation(userId));
    }

    @GetMapping
    ResponseEntity<UserListResponse> getUserList(
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(userService.getUserList(sortBy, direction, page, size));
    }

    @PostMapping
    ResponseEntity<Void> registerUser(@RequestBody @Valid RegisterUserRequest registerUserRequest){
        userService.registerUser(registerUserRequest);
        return ResponseEntity.ok().build();
    }
}
