package br.com.brenonoccioli.desafioverticallogistica.mappers;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.ProductResponse;
import br.com.brenonoccioli.desafioverticallogistica.models.Product;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "value", target = "value", qualifiedByName = "convertToString")
    ProductResponse mapToResponse(Product product);

    @IterableMapping(elementTargetType = Product.class)
    List<ProductResponse> mapToListResponse(List<Product> products);

    @Named("convertToString")
    default String convertToString(BigDecimal value){
        return String.valueOf(value);
    }
}
