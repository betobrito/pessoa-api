package br.com.softplan.pessoaapi.web.rest;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.domain.dto.PessoaDTO;
import br.com.softplan.pessoaapi.service.PessoaService;
import br.com.softplan.pessoaapi.util.validation.Edit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.softplan.pessoaapi.util.Constantes.API_PESSOA;
import static br.com.softplan.pessoaapi.util.Constantes.SOURCE;

@RestController
@RequestMapping(SOURCE)
public class SourceResource {

    private final Logger log = LoggerFactory.getLogger(SourceResource.class);

    private final PessoaService pessoaService;

    public SourceResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<String> source() {
        log.debug("Chamada para método source, url para código da aplicação.");
        return  ResponseEntity.ok("https://github.com/betobrito/pessoa-api");
    }
}
