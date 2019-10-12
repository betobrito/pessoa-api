package br.com.softplan.pessoaapi.domain;

import br.com.softplan.pessoaapi.util.exception.InvalidDateOfBirthException;

import java.util.Calendar;
import java.util.Date;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA;

public class DataNascimento {

    private Date dataNascimento;

    private DataNascimento(Date dataNascimento) {
        lancarExceptionCasoDataNascimentoSejaInvalida(dataNascimento);
        this.dataNascimento = dataNascimento;
    }

    private void lancarExceptionCasoDataNascimentoSejaInvalida(Date dataNascimento) {
        if(ehInvalido(dataNascimento)){
            throw new InvalidDateOfBirthException(MSG_DATA_NASCIMENTO_INFORMADO_EH_INVALIDA);
        }
    }

    private boolean ehInvalido(Date dataNascimento) {
        return dataNascimento == null || Calendar.getInstance().before(getCalendar(dataNascimento));
    }

    private Calendar getCalendar(Date dataNascimento) {
        final Calendar dataNascimentoInformada = Calendar.getInstance();
        dataNascimentoInformada.setTime(dataNascimento);
        return dataNascimentoInformada;
    }

    public static DataNascimento of(Date dataNascimento) {
        return new DataNascimento(dataNascimento);
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}
