package br.com.brenonoccioli.desafioverticallogistica.helpers;

import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static br.com.brenonoccioli.desafioverticallogistica.helpers.OrderHelper.calculateTotalPrice;
import static org.junit.jupiter.api.Assertions.*;

class OrderHelperTest {

    @Test
    void testConstructor(){
        assertDoesNotThrow(OrderHelper::new);
    }

    @Test
    void testCalculateTotalPrice(){
        List<Product> products = List.of(Product.builder().value(BigDecimal.TEN).build(),
                Product.builder().value(new BigDecimal("100.31")).build(),
                Product.builder().value(new BigDecimal("20.21")).build());

        BigDecimal totalPrice = calculateTotalPrice(products);

        assertEquals(new BigDecimal("130.52"), totalPrice);
    }

}