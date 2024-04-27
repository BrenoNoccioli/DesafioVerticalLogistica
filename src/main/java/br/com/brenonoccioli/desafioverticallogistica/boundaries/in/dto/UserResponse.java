package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long user_id;
    private String name;
    private List<OrderResponse> orders;
}
