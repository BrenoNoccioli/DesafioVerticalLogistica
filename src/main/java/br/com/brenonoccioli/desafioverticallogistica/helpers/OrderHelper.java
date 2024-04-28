package br.com.brenonoccioli.desafioverticallogistica.helpers;

import br.com.brenonoccioli.desafioverticallogistica.models.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderHelper {

    public static BigDecimal calculateTotalPrice(List<Product> products){
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products){
            total = total.add(product.getValue());
        }
        return total;
    }
}
