package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;

public class ProductMapper {

    public static ProductResponse mapToProductResponse(Product product){
        if (product == null){
            return null;
        } else {
            return ProductResponse.builder()
                    .id(product.getId())
                    .value(String.valueOf(product.getValue()))
                    .build();
        }
    }
}
