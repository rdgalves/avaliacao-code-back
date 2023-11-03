package com.code.avaliacao.enums;

import lombok.Getter;

@Getter
public enum StatusProjetoEnum {
    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusProjetoEnum(String descricao) {
        this.descricao = descricao;
    }
}
