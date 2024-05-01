package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.helpers.OrderHelper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderEntity;
import static br.com.brenonoccioli.desafioverticallogistica.OrderTestData.getOrderResponse;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProduct;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProductResponse;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {

    @Test
    void testConstructor(){
        assertDoesNotThrow(OrderMapper::new);
    }

    @ParameterizedTest
    @MethodSource("sourceTestMapper")
    void testMapper(OrderEntity entity, List<ProductResponse> productsResponse, OrderResponse expected){
        OrderResponse resp = OrderMapper.mapToOrderResponse(entity, productsResponse);
        assertEquals(expected, resp);
    }

    public static Stream<Arguments> sourceTestMapper() {
        OrderEntity entity = getOrderEntity(List.of(getProduct()));
        ProductResponse productResponse = getProductResponse();
        OrderResponse expected = getOrderResponse(List.of(getProductResponse()));
        return Stream.of(Arguments.of(null, null, null),
                Arguments.of(entity, List.of(productResponse), expected));
    }
}