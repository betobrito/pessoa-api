package br.com.softplan.pessoaapi.domain.converter;

import br.com.softplan.pessoaapi.domain.Documento;

import javax.persistence.AttributeConverter;

public class DocumentoConverter implements AttributeConverter<Documento, String> {
    @Override
    public String convertToDatabaseColumn(Documento documento) {
        return documento == null ? null : documento.toString();
    }

    @Override
    public Documento convertToEntityAttribute(String documento) {
        return documento == null ? null : Documento.of(documento);
    }
}
