package com.code.avaliacao.enums;

import lombok.Getter;

@Getter
public enum RiscoProjetoEnum {
    BAIXO_RISCO("Baixo Risco"),
    MEDIO_RISCO("Médio Risco"),
    ALTO_RISCO("Alto Risco");

    private final String descricao;

    RiscoProjetoEnum(String descricao) {
        this.descricao = descricao;
    }


}
