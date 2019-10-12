package br.com.softplan.pessoaapi.service;

import br.com.softplan.pessoaapi.domain.Pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa find(Long id);

    Pessoa create(Pessoa pessoa);

    Pessoa edit(Long id, Pessoa pessoa);

    void delete(Long id);

    List<Pessoa> list();
}
