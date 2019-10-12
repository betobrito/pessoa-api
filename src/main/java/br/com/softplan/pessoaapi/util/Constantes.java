package br.com.softplan.pessoaapi.util;

public interface Constantes {

    String API_PESSOA = "api/pessoa";

    interface Mensagens{
        String MSG_DOCUMENTO_INFORMADO_EH_INVALIDO = "Documento informado é inválido.";
        String MSG_EMAIL_INFORMADO_EH_INVALIDO = "Email informado é inválido.";
        String MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA = "Data de Nascimento informada é inválida.";
        String MSG_NAO_FOI_ENCONTRADA_PESSOA_COM_ESTE_ID = "Não foi encontrada pessoa com este id.";
        String MSG_CPF_DA_PESSOA_INFORMADA_JA_EXISTE = "Cpf da pessoa informada já existe.";
    }
}
