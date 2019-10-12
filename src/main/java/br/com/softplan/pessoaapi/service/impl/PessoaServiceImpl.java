package br.com.softplan.pessoaapi.service.impl;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.repository.PessoaRepository;
import br.com.softplan.pessoaapi.service.PessoaService;
import br.com.softplan.pessoaapi.util.exception.NegocioException;
import br.com.softplan.pessoaapi.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_CPF_DA_PESSOA_INFORMADA_JA_EXISTE;
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
        validarSeCpfInformadoJaExiste(pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa edit(Long id, Pessoa pessoaAlterada) {
        Pessoa pessoaConsultada = find(id);
        atribuindoDadosDaPessoaAlteradaAhPessoaConsultada(pessoaAlterada, pessoaConsultada);
        return pessoaConsultada;
    }

    @Override
    public void delete(Long id) {
        Pessoa pessoaConsultada = find(id);
        pessoaRepository.delete(pessoaConsultada);
    }

    @Override
    public List<Pessoa> list() {
        return pessoaRepository.findAll();
    }

    private void validarSeCpfInformadoJaExiste(Pessoa pessoa) {
        final Pessoa pessoaConsultada = pessoaRepository.findByCpf(pessoa.getCpf());
        if(pessoaConsultada != null){
            throw new NegocioException(MSG_CPF_DA_PESSOA_INFORMADA_JA_EXISTE);
        }
    }

    private void atribuindoDadosDaPessoaAlteradaAhPessoaConsultada(Pessoa pessoaAlterada, Pessoa pessoaConsultada) {
        pessoaConsultada.nome(pessoaAlterada.getNome())
                .email(pessoaAlterada.getEmail())
                .sexo(pessoaAlterada.getSexo())
                .dataNascimeto(pessoaAlterada.getDataNascimento())
                .nacionalidade(pessoaAlterada.getNacionalidade())
                .naturalidade(pessoaAlterada.getNaturalidade());
    }
}
