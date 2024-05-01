package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProduct;
import static br.com.brenonoccioli.desafioverticallogistica.ProductTestData.getProductResponse;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void testConstructor(){
        assertDoesNotThrow(ProductMapper::new);
    }

    @ParameterizedTest
    @MethodSource("sourceTestMapper")
    void testMapper(Product entity, ProductResponse expected){
        ProductResponse resp = ProductMapper.mapToProductResponse(entity);
        assertEquals(expected, resp);
    }

    public static Stream<Arguments> sourceTestMapper() {
        Product entity = getProduct();
        ProductResponse expected = getProductResponse();
        return Stream.of(Arguments.of(null, null),
                Arguments.of(entity, expected));
    }
}