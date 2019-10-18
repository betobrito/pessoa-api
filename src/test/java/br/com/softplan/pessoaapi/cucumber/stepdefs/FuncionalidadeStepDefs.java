package br.com.softplan.pessoaapi.cucumber.stepdefs;

import br.com.softplan.pessoaapi.domain.dto.ErroDTO;
import br.com.softplan.pessoaapi.domain.dto.PessoaDTO;
import br.com.softplan.pessoaapi.util.JsonConverter;
import cucumber.api.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FuncionalidadeStepDefs extends StepDefs {

    @Before
    public void context() {
        helperContext.limparBaseDados();
    }

    @Dado("que exista uma pessoa cadastrada")
    public void queExistaUmaPessoaCadastrada() {
        montadorContexto.adicionarPessoa();
    }

    @Dado("que uma pessoa de id {string}")
    public void queUmaPessoaDeId(String id) throws Exception {
        this.actions = mockGet("/api/pessoa/{id}", id);
    }

    @Então("deveria retornar uma pessoa de nome {string}")
    public void deveriaRetornarUmaPessoaDeNome(String nomePessoa) throws Exception {
        this.actions.andExpect(status().isOk());
        PessoaDTO pessoaDTO = JsonConverter.asJsonToClass(this.actions.andReturn().getResponse().getContentAsString(), PessoaDTO.class);
        assertEquals(nomePessoa, pessoaDTO.getNome());
    }

    @Então("deveria retornar uma exception not found")
    public void deveriaRetornarUmaExceptionNotFound() throws Exception {
        this.actions.andExpect(status().isNotFound());
        ErroDTO erroDTO = JsonConverter.asJsonToClass(this.actions.andReturn().getResponse().getContentAsString(), ErroDTO.class);
        assertEquals(MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID, erroDTO.getErro());
    }
}
