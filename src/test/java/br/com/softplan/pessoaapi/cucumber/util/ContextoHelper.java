package br.com.softplan.pessoaapi.cucumber.util;

import java.util.Date;

public interface ContextoHelper {

    void limparBaseDados();

    void inserirPessoa(String nome, String sexo, String cpf, String email, String nacionalidade, String naturalidade, Date dataNascimento);
}
