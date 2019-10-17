package br.com.softplan.pessoaapi.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static br.com.softplan.pessoaapi.util.Constantes.URL_API_HTTPS_GITHUB_COM_BETOBRITO_PESSOA_API;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SourceResourceTest {

    private SourceResource sourceResource;

    @Before
    public void context() {
        this.sourceResource = new SourceResource();
    }

    @Test
    public void deveriaChamarOhMetodoSourceRetornandoUrlDoCodigoFonte() {
        ResponseEntity resultado = sourceResource.source();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(URL_API_HTTPS_GITHUB_COM_BETOBRITO_PESSOA_API, resultado.getBody());
    }
}
