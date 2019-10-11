package br.com.softplan.pessoaapi.domain.converter;

import br.com.softplan.pessoaapi.domain.DataNascimento;

import javax.persistence.AttributeConverter;
import java.time.LocalDate;

public class DataNascimentoConverter implements AttributeConverter<DataNascimento, LocalDate> {
    @Override
    public LocalDate convertToDatabaseColumn(DataNascimento dataNascimento) {
        return dataNascimento == null ? null : dataNascimento.getDataNascimento();
    }

    @Override
    public DataNascimento convertToEntityAttribute(LocalDate dataNascimento) {
        return dataNascimento == null ? null : DataNascimento.of(dataNascimento);
    }
}
