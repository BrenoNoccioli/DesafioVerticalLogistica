package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.OrderResponse;
import br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper;
import br.com.brenonoccioli.desafioverticallogistica.models.OrderEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "totalPrice", target = "total")
    @Mapping(source = "date", target = "date", qualifiedByName = "mapDateToString")
    OrderResponse mapToResponse(OrderEntity entity);

    @IterableMapping(elementTargetType = OrderResponse.class)
    List<OrderResponse> mapToListResponse(List<OrderEntity> entities);

    @Named("mapDateToString")
    default String mapDateToString(LocalDate date){
        return DateHelper.convertLocalDateToString(date, "yyyy-MM-dd");
    }
}
