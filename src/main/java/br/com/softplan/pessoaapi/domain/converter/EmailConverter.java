package br.com.softplan.pessoaapi.domain.converter;

import br.com.softplan.pessoaapi.domain.Email;

import javax.persistence.AttributeConverter;

public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email == null ? null : email.toString();
    }

    @Override
    public Email convertToEntityAttribute(String email) {
        return email == null ? null : Email.of(email);
    }
}
