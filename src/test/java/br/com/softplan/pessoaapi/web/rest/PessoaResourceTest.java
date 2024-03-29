package br.com.softplan.pessoaapi.web.rest;

import br.com.softplan.pessoaapi.domain.DataNascimento;
import br.com.softplan.pessoaapi.domain.Documento;
import br.com.softplan.pessoaapi.domain.Email;
import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.domain.dto.PessoaDTO;
import br.com.softplan.pessoaapi.service.PessoaService;
import br.com.softplan.pessoaapi.util.exception.InvalidDateOfBirthException;
import br.com.softplan.pessoaapi.util.exception.InvalidDocumentException;
import br.com.softplan.pessoaapi.util.exception.InvalidEmailException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.*;
import static br.com.softplan.pessoaapi.util.ConstantesTeste.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaResourceTest {

    @Mock
    private PessoaService pessoaServiceMock;

    private PessoaResource pessoaResource;
    private PessoaDTO pessoaDTO;
    private Pessoa pessoa;
    private Optional<Pessoa> optionalPessoa;
    private List<Pessoa> pessoas;

    @Before
    public void context() {
        this.pessoaResource = new PessoaResource(pessoaServiceMock);
        this.pessoa = new Pessoa().email(Email.of(EMAIL_VALIDO))
                                  .dataNascimeto(DataNascimento.of(Calendar.getInstance().getTime()))
                                  .cpf(Documento.of(CPF_13785310005));
        this.pessoaDTO = new PessoaDTO(pessoa);
        this.optionalPessoa = Optional.of(pessoa);
        this.pessoas = new ArrayList<>();
    }

    @Test
    public void deveriaChamarOhMetodoFindDelegandoParaOhServicoRetornandoUmaPessoaDTO() {
        when(pessoaServiceMock.find(ID_UM)).thenReturn(pessoa);

        ResponseEntity resultado = pessoaResource.find(ID_UM);

        verify(pessoaServiceMock).find(ID_UM);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pessoaDTO, resultado.getBody());
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComDocumentoValido() throws URISyntaxException {
        when(pessoaServiceMock.create(any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity resultado = pessoaResource.create(pessoaDTO);

        verify(pessoaServiceMock).create(any(Pessoa.class));
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(pessoaDTO, resultado.getBody());
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComDocumentoInvalido() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataValida();
            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDocumentException e){
            assertEquals(MSG_DOCUMENTO_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComEmailInvalidoSemArroba() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailInvalidoSemArroba();
            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidEmailException e){
            assertEquals(MSG_EMAIL_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComEmailInvalidoSemPonto() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailInvalidoSemPontos();
            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidEmailException e){
            assertEquals(MSG_EMAIL_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComDocumentoValido() throws URISyntaxException {
        when(pessoaServiceMock.edit(any(Long.class), any(Pessoa.class))).thenReturn(pessoa);

        ResponseEntity resultado = pessoaResource.edit(ID_UM, pessoaDTO);

        verify(pessoaServiceMock).edit(any(Long.class), any(Pessoa.class));
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(pessoaDTO, resultado.getBody());
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComDocumentoInvalido() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataValida();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDocumentException e){
            assertEquals(MSG_DOCUMENTO_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComEmailInvalidoSemArroba() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailInvalidoSemArroba();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidEmailException e){
            assertEquals(MSG_EMAIL_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComEmailInvalidoSemPonto() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailInvalidoSemPontos();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidEmailException e){
            assertEquals(MSG_EMAIL_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoDeleteDelegandoParaOhServicoRetornandoStatusOk() throws URISyntaxException {
        ResponseEntity resultado = pessoaResource.delete(ID_UM);

        verify(pessoaServiceMock).delete(any(Long.class));
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    @Test
    public void deveriaChamarOhMetodoListDelegandoParaOhServicoRetornandoListaVazia() throws URISyntaxException {
        when(pessoaServiceMock.list()).thenReturn(pessoas);
        ResponseEntity resultado = pessoaResource.list();

        verify(pessoaServiceMock).list();
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(pessoas, resultado.getBody());
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComDataNascimentoInvalida() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataInvalida();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDateOfBirthException e){
            assertEquals(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoEditDelegandoParaOhServicoRetornandoUmaPessoaEditadaComDataNascimentoAvancada() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataAposDataAtual();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDateOfBirthException e){
            assertEquals(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComDataNascimentoInvalida() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataInvalida();
            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDateOfBirthException e){
            assertEquals(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoCreateDelegandoParaOhServicoRetornandoUmaPessoaInseridaComDataNascimentoAvancada() throws URISyntaxException {
        try{
            limparObjetosPessoaMantendoEmailValidoIhDataAposDataAtual();
            when(pessoaServiceMock.create(any(Pessoa.class))).thenReturn(pessoa);

            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDateOfBirthException e){
            assertEquals(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA, e.getMessage());
        }
    }

    private void limparObjetosPessoaMantendoEmailValidoIhDataValida() {
        this.pessoa = new Pessoa();
        this.pessoa.setEmail(Email.of(EMAIL_VALIDO));
        this.pessoa.setCpf(Documento.of(CPF_13785310005_INVALIDO));
        this.pessoa.setDataNascimento(DataNascimento.of(Calendar.getInstance().getTime()));
        this.pessoaDTO = new PessoaDTO(pessoa);
    }

    private void limparObjetosPessoaMantendoEmailInvalidoSemArroba() {
        this.pessoa = new Pessoa();
        this.pessoa.setEmail(Email.of(EMAIL_INVALIDO_SEM_ARROBA));
        this.pessoaDTO = new PessoaDTO(pessoa);
    }

    private void limparObjetosPessoaMantendoEmailInvalidoSemPontos() {
        this.pessoa = new Pessoa();
        this.pessoa.setEmail(Email.of(EMAIL_INVALIDO_SEM_PONTOS));
        this.pessoaDTO = new PessoaDTO(pessoa);
    }

    private void limparObjetosPessoaMantendoEmailValidoIhDataInvalida() {
        this.pessoa = new Pessoa();
        this.pessoa.setEmail(Email.of(EMAIL_VALIDO));
        this.pessoaDTO = new PessoaDTO(pessoa);
    }

    private void limparObjetosPessoaMantendoEmailValidoIhDataAposDataAtual() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.getInstance().get(Calendar.YEAR)+1, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));
        this.pessoa = new Pessoa().email(Email.of(EMAIL_VALIDO))
                                .dataNascimeto(DataNascimento.of(calendar.getTime()))
                                .cpf(Documento.of(CPF_13785310005));
        this.pessoaDTO = new PessoaDTO(pessoa);
    }
}
