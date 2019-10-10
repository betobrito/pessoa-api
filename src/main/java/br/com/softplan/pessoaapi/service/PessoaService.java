package br.com.softplan.pessoaapi.service;

import br.com.softplan.pessoaapi.domain.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    Optional<Pessoa> find(Long id);

    Pessoa create(Pessoa pessoa);

    Pessoa edit(Long id, Pessoa pessoa);

    void delete(Long id);

    List<Pessoa> list();
}
