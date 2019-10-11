package br.com.softplan.pessoaapi.domain;

import br.com.softplan.pessoaapi.util.exception.InvalidDateOfBirthException;

import java.time.LocalDate;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA;

public class DataNascimento {

    private LocalDate dataNascimento;

    private DataNascimento(LocalDate dataNascimento) {
        lancarExceptionCasoDataNascimentoSejaInvalida(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    private void lancarExceptionCasoDataNascimentoSejaInvalida(LocalDate dataNascimento) {
        if(ehInvalido(dataNascimento)){
            throw new InvalidDateOfBirthException(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA);
        }
    }

    private boolean ehInvalido(LocalDate dataNascimento) {
        return dataNascimento == null || LocalDate.now().isBefore(dataNascimento);
    }

    public static DataNascimento of(LocalDate dataNascimento) {
        return new DataNascimento(dataNascimento);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
