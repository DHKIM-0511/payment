package me.nrz.pay_demo.user.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import me.nrz.pay_demo.user.entity.User;
import org.springframework.data.domain.Page;

@Getter
public class UserListResponse {
    private final List<UserResponse> users;
    private final long totalElements;
    private final long totalPages;
    private final long currentPage;
    private final int pageSize;
    private final boolean hasNext;
    private final boolean hasPrev;

    public UserListResponse(Page<User> userPage){
        this.users = userPage.get().map(UserResponse::new)
            .collect(Collectors.toList());
        this.totalElements = userPage.getTotalElements();
        this.totalPages = userPage.getTotalPages();
        this.currentPage = userPage.getNumber();
        this.pageSize = userPage.getSize();
        this.hasNext = userPage.hasNext();
        this.hasPrev = userPage.hasPrevious();
    }
}
