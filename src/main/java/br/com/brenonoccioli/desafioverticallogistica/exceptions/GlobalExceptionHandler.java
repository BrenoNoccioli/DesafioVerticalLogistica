package br.com.brenonoccioli.desafioverticallogistica.exceptions;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> handleGlobalException(GlobalException ex){
        Message message = Message.builder().message(ex.getMessage()).build();
        return ResponseEntity.status(ex.getStatus()).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOthersExceptions(Exception ex){
        Message message = Message.builder().message(ex.getMessage()).build();
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(message);
    }
}
