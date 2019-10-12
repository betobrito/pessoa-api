package br.com.softplan.pessoaapi.domain.converter;

import br.com.softplan.pessoaapi.domain.DataNascimento;

import javax.persistence.AttributeConverter;
import java.util.Date;

public class DataNascimentoConverter implements AttributeConverter<DataNascimento, Date> {
    @Override
    public Date convertToDatabaseColumn(DataNascimento dataNascimento) {
        return dataNascimento == null ? null : dataNascimento.getDataNascimento();
    }

    @Override
    public DataNascimento convertToEntityAttribute(Date dataNascimento) {
        return dataNascimento == null ? null : DataNascimento.of(dataNascimento);
    }
}
