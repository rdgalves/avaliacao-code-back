package com.code.avaliacao.model;

import com.code.avaliacao.enums.RiscoProjetoEnum;
import com.code.avaliacao.enums.StatusProjetoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_previsao_fim")
    private Date dataPrevisaoFim;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(length = 5000)
    private String descricao;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private StatusProjetoEnum status;

    private Float orcamento;

    @Column(length = 45)
    @Enumerated(EnumType.STRING)
    private RiscoProjetoEnum risco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa")
    )
    private Set<Pessoa> membros = new HashSet<>();

}
