package br.com.softplan.pessoaapi.domain;

import br.com.softplan.pessoaapi.util.exception.InvalidDocumentException;

import java.util.Objects;
import java.util.regex.Pattern;

import static br.com.softplan.pessoaapi.util.Constantes.Mensagens.MSG_DOCUMENTO_INFORMADO_EH_INVALIDO;

public class Documento {

    private String numeroDocumento;

    private Documento(String documento) {
        lancarExceptionDocumentoInvalidoCasoSejaInvalido(documento);
        this.numeroDocumento = documento;
    }

    private void lancarExceptionDocumentoInvalidoCasoSejaInvalido(String documento) {
        if(documento != null){
            validarSeDocumento(documento);
        }
    }

    private void validarSeDocumento(String documento) {
        if (!ehValido(documento)){
            throw new InvalidDocumentException(MSG_DOCUMENTO_INFORMADO_EH_INVALIDO);
        }
    }

    public static Documento of(String document) {
        return new Documento(document);
    }

    private boolean ehValido(String documento) {
        return Pattern.matches( "\\d+", documento ) &&
                isCpf(documento);
    }

    private boolean isCpf(String documento) {
        return documento.length() == 11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return Objects.equals(numeroDocumento, documento.numeroDocumento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroDocumento);
    }

    @Override
    public String toString() {
        return this.numeroDocumento;
    }
}
