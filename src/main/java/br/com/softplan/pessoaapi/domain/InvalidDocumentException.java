package br.com.softplan.pessoaapi.domain;

public class InvalidDocumentException extends RuntimeException {

    public InvalidDocumentException(String message) {
        super(message);
    }

}
