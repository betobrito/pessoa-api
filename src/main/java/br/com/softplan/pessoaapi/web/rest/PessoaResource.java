package br.com.softplan.pessoaapi.web.rest;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.domain.dto.PessoaDTO;
import br.com.softplan.pessoaapi.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static br.com.softplan.pessoaapi.util.Constantes.API_PESSOA;

@RestController
@RequestMapping(API_PESSOA)
public class PessoaResource {

    public static final String BARRA = "/";
    private final Logger log = LoggerFactory.getLogger(PessoaResource.class);

    private final PessoaService pessoaService;

    public PessoaResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> find(@PathVariable Long id) {
        log.debug("Chamada para método find, o qual busca uma pessoa específica de id : {}", id);
        final Pessoa pessoaRetornada = pessoaService.find(id);
        return  ResponseEntity.ok(PessoaDTO.of(pessoaRetornada));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO pessoa) throws URISyntaxException {
        log.debug("Chamada para método create, o qual criará uma pessoa de cpf: {}", pessoa.getCpf());
        final Pessoa pessoaInserida = pessoaService.create(pessoa.transformToEntity());
        return ResponseEntity
                .created(new URI(API_PESSOA + BARRA + pessoaInserida.getId()))
                .body(PessoaDTO.of(pessoaInserida));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> edit(@PathVariable Long id, @Valid @RequestBody PessoaDTO pessoa) throws URISyntaxException {
        log.debug("Chamada para método edit, o qual editará uma pessoa de cpf: {} e id {}", pessoa.getCpf(), id);
        final Pessoa pessoaEditada = pessoaService.edit(id, pessoa.transformToEntity());
        return ResponseEntity
                .created(new URI(API_PESSOA + BARRA + pessoaEditada.getId()))
                .body(PessoaDTO.of(pessoaEditada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("Chamada para método delete, o qual deletará uma pessoa de id {}", id);
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> list() {
        log.debug("Chamada para método list, o qual listará pessoas cadastradas na base de dados.");
        final List<Pessoa> listaRetornada = pessoaService.list();
        return ResponseEntity.ok(PessoaDTO.convert(listaRetornada));
    }
}
