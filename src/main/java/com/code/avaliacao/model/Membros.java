package com.code.avaliacao.model;

import com.code.avaliacao.model.ids.MembrosId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "membros")
@IdClass(MembrosId.class)
public class Membros {

    @Id
    @ManyToOne
    @JoinColumn(name = "idprojeto", referencedColumnName = "id")
    private Projeto projeto;

    @Id
    @ManyToOne
    @JoinColumn(name = "idpessoa", referencedColumnName = "id")
    private Pessoa pessoa;

}