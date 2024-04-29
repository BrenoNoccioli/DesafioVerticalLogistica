package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class OrderMapper {

    public static OrderResponse mapToResponse(OrderEntity entity, List<ProductResponse> products){
        if (entity == null) {
            return null;
        } else {
            return OrderResponse.builder()
                    .id(entity.getId())
                    .total(String.valueOf(entity.getTotalPrice()))
                    .date(mapDateToString(entity.getDate()))
                    .products(products)
                    .build();
        }
    }

    private static String mapDateToString(LocalDate date){
        return DateHelper.convertLocalDateToString(date, "yyyy-MM-dd");
    }
}
