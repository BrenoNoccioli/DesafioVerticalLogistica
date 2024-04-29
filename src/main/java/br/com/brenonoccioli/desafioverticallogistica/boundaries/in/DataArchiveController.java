package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.service.DataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/archives")
public class DataArchiveController {

    private final DataService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataArchiveController.class);

    @PostMapping()
    public ResponseEntity<?> inputData(@RequestBody String dataArchive) {

        LOGGER.info(String.format("Recebendo requisição para processar arquivo: %s", dataArchive));

        if (dataArchive == null || dataArchive.isEmpty()){
            //Lança Exception;
        }

        service.proccessData(dataArchive);

        return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo processado com sucesso!");
    }

}
