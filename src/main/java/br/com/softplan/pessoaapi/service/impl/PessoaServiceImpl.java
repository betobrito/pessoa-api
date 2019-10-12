package br.com.softplan.pessoaapi.service.impl;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.repository.PessoaRepository;
import br.com.softplan.pessoaapi.service.PessoaService;
import br.com.softplan.pessoaapi.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID;

@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa find(Long id) {
        final Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        return optionalPessoa.orElseThrow(() -> new NotFoundException(MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID));
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
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
