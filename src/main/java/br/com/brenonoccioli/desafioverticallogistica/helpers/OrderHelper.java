package br.com.brenonoccioli.desafioverticallogistica.helpers;

import br.com.brenonoccioli.desafioverticallogistica.models.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderHelper {

    public static BigDecimal calculateTotalPrice(List<ProductEntity> products){
        BigDecimal total = BigDecimal.ZERO;
        for (ProductEntity product : products){
            total = total.add(product.getValue());
        }
        return total;
    }
}
