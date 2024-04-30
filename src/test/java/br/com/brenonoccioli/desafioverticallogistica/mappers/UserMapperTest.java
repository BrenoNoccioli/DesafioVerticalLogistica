package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.OrderTestData;
import br.com.brenonoccioli.desafioverticallogistica.UserTestData;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import br.com.brenonoccioli.desafioverticallogistica.models.UserEntity;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderEntity;
import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderResponse;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProduct;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProductResponse;
import static br.com.brenonoccioli.desafioverticallogistica.UserTestData.getUserEntity;
import static br.com.brenonoccioli.desafioverticallogistica.UserTestData.getUserResponse;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @ParameterizedTest
    @MethodSource("sourceTestMapper")
    void testMapper(UserEntity entity, UserResponse expected){
        UserResponse resp = UserMapper.mapToUserResponse(entity, List.of(getOrderResponse(List.of(getProductResponse()))));
        assertEquals(expected, resp);
    }

    public static Stream<Arguments> sourceTestMapper() {
        UserEntity entity = getUserEntity(Set.of(getOrderEntity(List.of(getProduct()))));
        UserResponse expected = getUserResponse(List.of(getOrderResponse(List.of(getProductResponse()))));
        return Stream.of(Arguments.of(null, null),
                Arguments.of(entity, expected));
    }

}