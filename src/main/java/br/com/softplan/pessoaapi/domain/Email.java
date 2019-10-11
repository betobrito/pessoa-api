package br.com.softplan.pessoaapi.domain;

import br.com.softplan.pessoaapi.util.exception.InvalidEmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_EMAIL_INFORMADO_EH_INVALIDO;

public class Email {

    private String email;

    private Email(String email) {
        lancarExceptionCasoEmailInformadoSejaInvalido(email);
        this.email = email;
    }

    private void lancarExceptionCasoEmailInformadoSejaInvalido(String email) {
        if(email != null){
            validarEmailUtilizandoRegex(email);
        }
    }

    private void validarEmailUtilizandoRegex(String email) {
        if(!ehValido(email)){
            throw new InvalidEmailException(MSG_EMAIL_INFORMADO_EH_INVALIDO);
        }
    }

    public boolean ehValido(String email) {
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    @Override
    public String toString() {
        return this.email;
    }
}
