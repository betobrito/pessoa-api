package br.com.softplan.pessoaapi.cucumber.montador.impl;

import br.com.softplan.pessoaapi.cucumber.montador.MontadorContexto;
import br.com.softplan.pessoaapi.cucumber.util.ContextoHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

import static br.com.softplan.pessoaapi.util.ConstantesTeste.*;

@Component
public class MontadorContextoImpl implements MontadorContexto {

    public static final String MASCULINO = "M";
    public static final String NACIONALIDADE_BRASIL = "Brasil";
    public static final String NATURALIDADE_MACEIO = "Maceió";
    private ContextoHelper contextoHelper;

    public MontadorContextoImpl(ContextoHelper contextoHelper) {
        this.contextoHelper = contextoHelper;
    }

    @Override
    public void adicionarPessoa() {
        contextoHelper.inserirPessoa(NOME_PESSOA_TESTE, MASCULINO, CPF_13785310005, EMAIL_VALIDO, NACIONALIDADE_BRASIL, NATURALIDADE_MACEIO, new Date());
    }
}
