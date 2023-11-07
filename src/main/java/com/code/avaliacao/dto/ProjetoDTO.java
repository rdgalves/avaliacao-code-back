package com.code.avaliacao.dto;

import com.code.avaliacao.enums.RiscoProjetoEnum;
import com.code.avaliacao.enums.StatusProjetoEnum;
import com.code.avaliacao.model.Pessoa;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<PessoaDTO> membros = new HashSet<>();
}
