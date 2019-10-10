package br.com.softplan.pessoaapi.service;

import br.com.softplan.pessoaapi.domain.Pessoa;

import java.util.Optional;

public interface PessoaService {

    Optional<Pessoa> find(Long id);

    Pessoa create(Pessoa pessoa);
}
