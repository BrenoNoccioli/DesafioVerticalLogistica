package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "total", "date", "products"})
public class OrderResponse {
    @JsonProperty("order_id")
    private Long id;
    private String total;
    private String date;
    private List<ProductResponse> products;
}
