package me.nrz.pay_demo.user.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.nrz.pay_demo.user.dto.request.RegisterUserRequest;
import me.nrz.pay_demo.user.dto.response.UserListResponse;
import me.nrz.pay_demo.user.dto.response.UserResponse;
import me.nrz.pay_demo.user.entity.User;
import me.nrz.pay_demo.user.repository.UserQueryDslRepository;
import me.nrz.pay_demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserQueryDslRepository userQueryDslRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse getUserInformation(int userId) {
        User foundedUser = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        foundedUser.increaseViewCount();
        return new UserResponse(foundedUser);
    }

    @Override
    public UserListResponse getUserList(String sortBy, String direction, int page, int size) {
        List<String> allowedSortFields = List.of("name", "viewCount", "createdAt");
        if (!allowedSortFields.contains(sortBy)) throw new IllegalArgumentException("Invalid sort field");

        return new UserListResponse(userQueryDslRepository.findUsersOrderByCondition(sortBy, direction, page, size));
    }

    @Override
    @Transactional
    public void registerUser(RegisterUserRequest registerUserRequest) {
        userRepository.save(new User(registerUserRequest.getName()));
    }
}
