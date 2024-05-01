package br.com.brenonoccioli.desafioverticallogistica.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class RequestParamsException extends GlobalException{
    public RequestParamsException(HttpStatus status, String message) {
        super(status, message);
    }

    public static RequestParamsException create(){
        return new RequestParamsException(BAD_REQUEST, "Os parâmetros informados são inválidos");
    }

}
