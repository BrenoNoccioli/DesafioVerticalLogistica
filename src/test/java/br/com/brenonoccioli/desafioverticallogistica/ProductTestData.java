package br.com.brenonoccioli.desafioverticallogistica;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;

import java.math.BigDecimal;

public class ProductTestData {
    public static ProductResponse getProductResponse(){
        return ProductResponse.builder()
                .id(1L)
                .value("10")
                .build();
    }

    public static Product getProduct(){
        return Product.builder()
                .id(1L)
                .value(BigDecimal.TEN)
                .build();
    }
}
