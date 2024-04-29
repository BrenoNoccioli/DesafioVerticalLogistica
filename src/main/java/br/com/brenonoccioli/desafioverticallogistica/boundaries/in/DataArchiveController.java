package br.com.brenonoccioli.desafioverticallogistica.boundaries.in;

import br.com.brenonoccioli.desafioverticallogistica.service.DataService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/archives")
public class DataArchiveController {

    private final DataService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataArchiveController.class);

    @PostMapping
    public ResponseEntity<?> inputData(@RequestBody String dataArchive) {

        LOGGER.info(String.format("Recebendo requisição para processar arquivo: %s", dataArchive));

        if (dataArchive == null || dataArchive.isEmpty()){
            //Lança Exception;
        }

        List<String> resp = service.proccessData(dataArchive);

        if (!resp.isEmpty()){
            LOGGER.error(String.format("Arquivo processado parcialmente com sucesso." +
                    "\nLinhas não processadas:\n%s", resp));
            return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo processado parcialmente com sucesso.\n" +
                    "Verifique os logs para mais informações");
        }

        LOGGER.info("Arquivo processado inteiramente com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo processado inteiramente com sucesso!");
    }
}
