package br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Message {
    String message;
}
