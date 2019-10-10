package br.com.softplan.pessoaapi.service.impl;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.service.PessoaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {
    @Override
    public Optional<Pessoa> find(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Pessoa edit(Long id, Pessoa pessoa) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pessoa> list() {
        throw new UnsupportedOperationException();
    }
}
