package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.exceptions.RequestParamsException;
import br.com.brenonoccioli.desafioverticallogistica.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.brenonoccioli.desafioverticallogistica.helpers.DateHelper.isValidDateParams;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/orders")
    public ResponseEntity<List<UserResponse>> getAllOrdersByUsers(
            @RequestParam(name = "init_date", required = false) String initDate,
            @RequestParam(name = "finish_date", required = false) String finishDate){
        LOGGER.info("Recebedo requisição para consulta de pedidos de usuários.");

        if (!isValidDateParams(initDate, finishDate)){
            LOGGER.error("Requisição inválida - parâmetros inválidos");
            throw RequestParamsException.create();
        }

        List<UserResponse> resp = userService.getAll(initDate, finishDate);

        LOGGER.info(String.format("Requisição concluída com sucesso: '%s'", resp));
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<UserResponse> getUserByOrderId(@PathVariable("orderId") Long orderId){
        LOGGER.info(String.format("Recebendo requisição para consulta de pedidos por id '%s'", orderId));

        UserResponse resp = userService.getByOrderId(orderId);

        LOGGER.info(String.format("Requisição concluída com sucesso: '%s'", resp));
        return ResponseEntity.ok().body(resp);
    }

}
