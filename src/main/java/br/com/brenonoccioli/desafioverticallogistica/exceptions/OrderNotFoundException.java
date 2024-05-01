package br.com.brenonoccioli.desafioverticallogistica.exceptions;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends GlobalException {
    public OrderNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

    public static OrderNotFoundException create(Long orderId){
        return new OrderNotFoundException(HttpStatus.NOT_FOUND,
                String.format("Pedido com id '%s' não encontrado", orderId));
    }
}
