package com.code.avaliacao.dto;

import com.code.avaliacao.enums.RiscoProjetoEnum;
import com.code.avaliacao.enums.StatusProjetoEnum;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoDTO {
    private Long id;
    private String nome;
    private Date dataInicio;
    private Date dataPrevisaoFim;
    private Date dataFim;
    private String descricao;
    private StatusProjetoEnum status;
    private Float orcamento;
    private RiscoProjetoEnum risco;
    private Long idGerente;
}
