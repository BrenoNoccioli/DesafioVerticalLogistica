package br.com.brenonoccioli.desafioverticallogistica;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.constants.ApplicationConstants;
import br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderTestData {
    public static OrderEntity getOrderEntity(List<Product> products){
        return OrderEntity.builder()
                .id(1L)
                .date(LocalDate.now())
                .totalPrice(BigDecimal.TEN)
                .products(products)
                .build();
    }

    public static OrderResponse getOrderResponse(List<ProductResponse> productsResponse){
        return OrderResponse.builder()
                .id(1L)
                .date(DateHelper.convertLocalDateToString(LocalDate.now(), ApplicationConstants.YYYY_MM_DD))
                .total("10")
                .products(productsResponse)
                .build();
    }

}
