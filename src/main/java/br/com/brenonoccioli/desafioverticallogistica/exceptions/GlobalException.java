package br.com.brenonoccioli.desafioverticallogistica.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
