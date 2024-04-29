package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {
    @JsonProperty("order_id")
    private Long id;
    private String total;
    private String date;
    private UserResponse user;
    private List<ProductResponse> products;
}
