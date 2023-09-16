package com.unifacisa.tap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AlunoExceptionHandler {

    private static final Integer ALUNO_NA0_ENCONTRADO_EXCEPTION_CODE = 1;

    private static final Integer ALUNO_GENERIC_EXCEPTION_CODE = 2;

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<Erro> handlerAlunoNaoEncontradoException(AlunoNaoEncontradoException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(getErro(exception, ALUNO_NA0_ENCONTRADO_EXCEPTION_CODE));
    }

    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<Erro> handlerAlunoException(AlunoException exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getErro(exception, ALUNO_GENERIC_EXCEPTION_CODE));
    }

    private Erro getErro(Exception exception, Integer code){
        return new Erro(exception.getMessage(), code);
    }
}
