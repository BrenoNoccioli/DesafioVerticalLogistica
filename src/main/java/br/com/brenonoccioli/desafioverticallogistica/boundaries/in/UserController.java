package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/orders")
    public ResponseEntity<List<UserResponse>> getOrdersData(){

        List<UserResponse> resp = userService.getAll();

        return ResponseEntity.ok().body(resp);
    }

}
