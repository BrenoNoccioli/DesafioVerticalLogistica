package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class Controller {

    private DataService service;

    @PostMapping
    public ResponseEntity<?> postData(String dataArchive) throws IOException {

        service.processData(dataArchive);

        return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo processado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getOrdersData(){
        return null;
    }
}
