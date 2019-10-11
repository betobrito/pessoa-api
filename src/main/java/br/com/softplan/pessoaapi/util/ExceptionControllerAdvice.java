package br.com.softplan.pessoaapi.util;

import br.com.softplan.pessoaapi.util.exception.InvalidDateOfBirthException;
import br.com.softplan.pessoaapi.util.exception.InvalidDocumentException;
import br.com.softplan.pessoaapi.util.exception.InvalidEmailException;
import br.com.softplan.pessoaapi.util.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound() {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidEmailException.class, InvalidDocumentException.class, InvalidDateOfBirthException.class})
    public void handleInvalidPessoa() {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, NullPointerException.class, RuntimeException.class})
    public void handle() {}
}
