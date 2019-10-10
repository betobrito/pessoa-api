package br.com.softplan.pessoaapi.web.rest;

import br.com.softplan.pessoaapi.domain.Documento;
import br.com.softplan.pessoaapi.domain.InvalidDocumentException;
import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.domain.PessoaDTO;
import br.com.softplan.pessoaapi.service.PessoaService;
import br.com.softplan.pessoaapi.util.Constantes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_DOCUMENTO_INFORMADO_EH_INVALIDO;
import static br.com.softplan.pessoaapi.util.ConstantesTeste.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
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
    private List<Pessoa> places;

    @Before
    public void context() {
        this.pessoaResource = new PessoaResource(pessoaServiceMock);
        this.pessoa = new Pessoa();
        this.pessoa.setCpf(Documento.of(CPF_13785310005));
        this.pessoaDTO = new PessoaDTO(pessoa);
        this.optionalPessoa = Optional.of(pessoa);
        this.places = new ArrayList<>();
    }

    @Test
    public void deveriaChamarOhMetodoFindDelegandoParaOhServicoRetornandoUmaPessoaDTO() {
        when(pessoaServiceMock.find(ID_UM)).thenReturn(optionalPessoa);

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
            limparObjetosPessoa();
            pessoaResource.create(pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDocumentException e){
            assertEquals(MSG_DOCUMENTO_INFORMADO_EH_INVALIDO, e.getMessage());
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
            limparObjetosPessoa();
            pessoaResource.edit(ID_UM, pessoaDTO);
            fail(MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO);
        }catch (InvalidDocumentException e){
            assertEquals(MSG_DOCUMENTO_INFORMADO_EH_INVALIDO, e.getMessage());
        }
    }

    @Test
    public void deveriaChamarOhMetodoDeleteDelegandoParaOhServicoRetornandoStatusOk() throws URISyntaxException {
        ResponseEntity resultado = pessoaResource.delete(ID_UM);

        verify(pessoaServiceMock).delete(any(Long.class));
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
    }

    private void limparObjetosPessoa() {
        this.pessoa = new Pessoa();
        this.pessoaDTO = new PessoaDTO(pessoa);
    }

}
