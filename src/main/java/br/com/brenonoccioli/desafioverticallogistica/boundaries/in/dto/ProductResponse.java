package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"id", "value"})
public class ProductResponse {
    @JsonProperty("product_id")
    private Long id;
    private String value;
}
