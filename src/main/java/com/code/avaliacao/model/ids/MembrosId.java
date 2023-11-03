package com.code.avaliacao.model.ids;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MembrosId implements Serializable {
    private Long projeto;
    private Long pessoa;
}
