package br.com.brenonoccioli.desafioverticallogistica.exceptions;

import org.springframework.http.HttpStatus;

public class RequestNotValidException extends GlobalException {
    public RequestNotValidException(HttpStatus status, String message) {
        super(status, message);
    }

    public static RequestNotValidException create(){
        return new RequestNotValidException(HttpStatus.BAD_REQUEST, "Requisição inválida.");
    }
}
