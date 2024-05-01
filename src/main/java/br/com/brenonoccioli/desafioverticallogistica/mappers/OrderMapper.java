package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;

import java.util.List;

import static br.com.brenonoccioli.desafioverticallogistica.constants.ApplicationConstants.YYYY_MM_DD;
import static br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper.convertLocalDateToString;


public class OrderMapper {

    public static OrderResponse mapToOrderResponse(OrderEntity entity, List<ProductResponse> products){
        if (entity == null) {
            return null;
        } else {
            return OrderResponse.builder()
                    .id(entity.getId())
                    .total(String.valueOf(entity.getTotalPrice()))
                    .date(convertLocalDateToString(entity.getDate(), YYYY_MM_DD))
                    .products(products)
                    .build();
        }
    }
}
