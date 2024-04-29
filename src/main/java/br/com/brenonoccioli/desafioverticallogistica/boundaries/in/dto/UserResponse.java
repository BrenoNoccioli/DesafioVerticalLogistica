package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    @JsonProperty("user_id")
    private Long id;
    private String name;
    private List<OrderResponse> orders;
}
