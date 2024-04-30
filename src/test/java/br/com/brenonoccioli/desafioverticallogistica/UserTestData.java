package br.com.brenonoccioli.desafioverticallogistica;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;

import java.util.List;
import java.util.Set;

public class UserTestData {
    public static UserEntity getUserEntity(Set<OrderEntity> orders){
        if (orders != null){
            return UserEntity.builder()
                    .id(1L)
                    .name("Fulano de Tal")
                    .orders(orders)
                    .build();
        }
        return UserEntity.builder()
                .id(1L)
                .name("Fulano de Tal")
                .build();
    }

    public static UserResponse getUserResponse(List<OrderResponse> ordersResponse){
        return UserResponse.builder()
                .id(1L)
                .name("Fulano de Tal")
                .orders(ordersResponse)
                .build();
    }
}
