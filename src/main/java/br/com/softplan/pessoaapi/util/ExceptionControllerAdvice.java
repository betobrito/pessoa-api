package br.com.softplan.pessoaapi.util;

import br.com.softplan.pessoaapi.domain.dto.ErroDTO;
import br.com.softplan.pessoaapi.util.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public void handleNotFound() {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({NegocioException.class})
    public ErroDTO handleNegocio(NegocioException e) {
        return new ErroDTO(e.getClass().getSimpleName(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidEmailException.class, InvalidDocumentException.class, InvalidDateOfBirthException.class})
    public ErroDTO handleInvalidPessoa(RuntimeException e) {
        return new ErroDTO(e.getClass().getSimpleName(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ErroDTO handleInvalidPessoa(HttpMessageNotReadableException e) {
        return new ErroDTO(e.getClass().getSimpleName(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ErroDTO> handleInvalidPessoa(MethodArgumentNotValidException exception) {
        List<ErroDTO> dtos = new ArrayList<>();
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            ErroDTO erro = new ErroDTO(e.getField(), messageSource.getMessage(e, LocaleContextHolder.getLocale()));
            dtos.add(erro);
        });
        return dtos;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, NullPointerException.class, RuntimeException.class})
    public void handle() {}
}
