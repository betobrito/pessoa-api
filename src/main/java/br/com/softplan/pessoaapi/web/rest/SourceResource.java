package br.com.softplan.pessoaapi.web.rest;

import br.com.softplan.pessoaapi.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.softplan.pessoaapi.util.Constantes.SOURCE;
import static br.com.softplan.pessoaapi.util.Constantes.URL_API_HTTPS_GITHUB_COM_BETOBRITO_PESSOA_API;

@RestController
@RequestMapping(SOURCE)
public class SourceResource {

    private final Logger log = LoggerFactory.getLogger(SourceResource.class);

    public SourceResource() {
    }

    @GetMapping
    public ResponseEntity<String> source() {
        log.debug("Chamada para método source, url para código da aplicação.");
        return  ResponseEntity.ok(URL_API_HTTPS_GITHUB_COM_BETOBRITO_PESSOA_API);
    }
}
