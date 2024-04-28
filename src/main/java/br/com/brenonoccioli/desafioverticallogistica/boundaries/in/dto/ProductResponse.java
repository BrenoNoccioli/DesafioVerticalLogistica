package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Long product_id;
    private String value;
}
