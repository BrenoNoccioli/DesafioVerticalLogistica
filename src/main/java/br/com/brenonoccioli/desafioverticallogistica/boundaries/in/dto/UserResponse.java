package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@JsonPropertyOrder({"id", "name", "orders"})
public class UserResponse {
    @JsonProperty("user_id")
    private Long id;
    private String name;
    private List<OrderResponse> orders;
}
