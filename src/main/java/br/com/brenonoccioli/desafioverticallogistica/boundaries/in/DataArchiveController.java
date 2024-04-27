package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.boundaries.in.dto.UserResponse;
import br.com.brenonoccioli.desafioverticallogistica.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DataArchiveController {

    private DataService service;

    @PostMapping("/archive")
    public ResponseEntity<?> inputData(@RequestBody String dataArchive) {

        if (dataArchive == null || dataArchive.isEmpty()){
            //Lança Exception;
        }

        service.proccessData(dataArchive);

        return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo processado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getOrdersData(){
        return null;
    }
}
