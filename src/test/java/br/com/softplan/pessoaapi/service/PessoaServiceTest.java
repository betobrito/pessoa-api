package br.com.softplan.pessoaapi.service;

import br.com.softplan.pessoaapi.domain.Pessoa;
import br.com.softplan.pessoaapi.repository.PessoaRepository;
import br.com.softplan.pessoaapi.service.impl.PessoaServiceImpl;
import br.com.softplan.pessoaapi.util.Constantes;
import br.com.softplan.pessoaapi.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID;
import static br.com.softplan.pessoaapi.util.ConstantesTeste.ID_UM;
import static br.com.softplan.pessoaapi.util.ConstantesTeste.MSG_NAO_DEVERIA_CHAMAR_ESSE_METODO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepositoryMock;
    private PessoaService pessoaService;
    private Pessoa pessoa;
    private Optional<Pessoa> optionalPessoa;
    private List<Pessoa> pessoas;

    @Before
    public void context() {
        this.pessoaService = new PessoaServiceImpl(pessoaRepositoryMock);
        this.pessoa = new Pessoa();
        this.optionalPessoa = Optional.of(pessoa);
        this.pessoas = new ArrayList<>();
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
}
