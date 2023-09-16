package com.unifacisa.tap.exception;

import lombok.Data;

@Data
public class Erro {

    public Erro(String mensagem, Integer codigo) {
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    private String mensagem;
    private Integer codigo;
}
