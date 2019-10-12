package br.com.softplan.pessoaapi.service;

import br.com.softplan.pessoaapi.domain.DataNascimento;
import br.com.softplan.pessoaapi.domain.Documento;
import br.com.softplan.pessoaapi.domain.Email;
import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.repository.PessoaRepository;
import br.com.softplan.pessoaapi.service.impl.PessoaServiceImpl;
import br.com.softplan.pessoaapi.util.exception.NegocioException;
import br.com.softplan.pessoaapi.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_CPF_DA_PESSOA_INFORMADA_JA_EXISTE;
import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID;
import static br.com.softplan.pessoaapi.util.ConstantesTeste.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepositoryMock;
    private PessoaService pessoaService;
    private Pessoa pessoa;
    private Pessoa pessoaAlterada;
    private Optional<Pessoa> optionalPessoa;
    private List<Pessoa> pessoas;

    @Before
    public void context() {
        this.pessoaService = new PessoaServiceImpl(pessoaRepositoryMock);
        this.pessoa = new Pessoa().cpf(Documento.of(CPF_13785310005));
        this.pessoaAlterada = new Pessoa().nome(NOME_PESSOA_TESTE)
                                          .email(Email.of(EMAIL_VALIDO))
                                          .dataNascimeto(DataNascimento.of(Calendar.getInstance().getTime()))
                                          .cpf(Documento.of(CPF_13785310005));
        this.optionalPessoa = Optional.of(pessoa);
        this.pessoas = new ArrayList<>();
        this.pessoas.add(pessoa);
    }

    @Test
    public void deveriaChamarOhMetodoFindDelegandoParaOhRepositorio() {
        when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(this.optionalPessoa);

        final Pessoa pessoaRetornada = pessoaService.find(ID_UM);

        verify(pessoaRepositoryMock).findById(ID_UM);
        assertEquals(pessoaRetornada, this.pessoa);
    }

    @Test
    public void deveriaChamarOhMetodoFindRetornandoUmNotFoundException() {
        when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(Optional.empty());

        try{
            pessoaService.find(ID_UM);

            verify(pessoaRepositoryMock).findById(ID_UM);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (NotFoundException e){
            assertEquals(MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoCreateRetornandoAhPessoaInserida() {
        when(pessoaRepositoryMock.save(any(Pessoa.class))).thenReturn(this.pessoa);

        final Pessoa pessoaRetornada = pessoaService.create(this.pessoa);

        verify(pessoaRepositoryMock).findByCpf(Documento.of(CPF_13785310005));
        verify(pessoaRepositoryMock).save(this.pessoa);
        assertEquals(pessoaRetornada, this.pessoa);
    }

    @Test
    public void deveriaChamarOhMetodoCreateRetornandoNegocioException() {
        try{
            when(pessoaRepositoryMock.findByCpf(Documento.of(CPF_13785310005))).thenReturn(this.pessoa);
            pessoaService.create(this.pessoa);
            verify(pessoaRepositoryMock).findByCpf(Documento.of(CPF_13785310005));
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (NegocioException e){
            assertEquals(MSG_CPF_DA_PESSOA_INFORMADA_JA_EXISTE, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoEditRetornandoAhPessoaAlterada() {
        when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(this.optionalPessoa);

        final Pessoa pessoaRetornada = pessoaService.edit(ID_UM, this.pessoaAlterada);

        verify(pessoaRepositoryMock).findById(ID_UM);
        assertEquals(this.pessoaAlterada, pessoaRetornada);
    }

    @Test
    public void deveriaChamarOhMetodoEditRetornandoNotFoundException() {
        try{
            when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(Optional.empty());
            pessoaService.edit(ID_UM, this.pessoaAlterada);

            verify(pessoaRepositoryMock).findById(ID_UM);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (NotFoundException e){
            assertEquals(MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoDeleteRetornandoNotFoundException() {
        try{
            when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(Optional.empty());
            pessoaService.delete(ID_UM);

            verify(pessoaRepositoryMock).findById(ID_UM);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (NotFoundException e){
            assertEquals(MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoDeleteDelegandoParaOhRepositorio() {
        when(pessoaRepositoryMock.findById(ID_UM)).thenReturn(optionalPessoa);
        pessoaService.delete(ID_UM);

        verify(pessoaRepositoryMock).findById(ID_UM);
        verify(pessoaRepositoryMock).delete(pessoa);
    }

    @Test
    public void deveriaChamarOhMetodoListDelegandoParaOhRepositorio() {
        when(pessoaRepositoryMock.findAll()).thenReturn(pessoas);
        final List<Pessoa> listaRetornada = pessoaService.list();

        verify(pessoaRepositoryMock).findAll();
        assertEquals(pessoas, listaRetornada);
    }
}
