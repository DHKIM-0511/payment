package me.nrz.pay_demo.user.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.nrz.pay_demo.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import static me.nrz.pay_demo.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<User> findUsersOrderByCondition(String sortBy, String direction, int page, int size){
        List<User> users = queryFactory.selectFrom(user)
            .orderBy(getOrderSpecifier(sortBy, direction))
            .offset((long) page * size)
            .limit(size)
            .fetch();

        long total = Optional.ofNullable(fetchCount().fetchOne()).orElse(0L);
        return new PageImpl<>(users, PageRequest.of(page, size), total);
    }

    private JPAQuery<Long> fetchCount() {
        return queryFactory
            .select(user.count())
            .from(user);
    }

    private OrderSpecifier<?> getOrderSpecifier(String sortBy, String direction) {
        Order order = direction.equalsIgnoreCase("desc") ? Order.DESC : Order.ASC;

        switch (sortBy) {
            case "name":
                return new OrderSpecifier<>(order, user.name);
            case "viewCount":
                return new OrderSpecifier<>(order, user.viewCount);
            case "createdAt":
                return new OrderSpecifier<>(order, user.createdAt);
            default:
                throw new IllegalArgumentException("Invalid sortBy value");
        }
    }
}
