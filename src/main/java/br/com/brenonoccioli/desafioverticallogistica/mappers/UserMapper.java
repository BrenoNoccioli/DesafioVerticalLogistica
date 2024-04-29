package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public static UserResponse mapToUserResponse(UserEntity entity, List<OrderResponse> orders) {
        if (entity == null) {
            return null;
        } else {
            return UserResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .orders(orders)
                    .build();
        }
    }
}
