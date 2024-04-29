package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {
    public static ProductResponse mapToResponse(Product product){
        if (product == null){
            return null;
        } else {
            return ProductResponse.builder()
                    .id(product.getId())
                    .value(convertToString(product.getValue()))
                    .build();
        }
    }


    private static String convertToString(BigDecimal value){
        return String.valueOf(value);
    }
}
