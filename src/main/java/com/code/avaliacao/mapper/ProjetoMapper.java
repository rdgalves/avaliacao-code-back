package com.code.avaliacao.mapper;

import com.code.avaliacao.dto.ProjetoDTO;
import com.code.avaliacao.model.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    ProjetoDTO toDTO(Projeto projeto);
    @Mapping(target = "gerente.id", source = "idGerente")
    Projeto toEntity(ProjetoDTO projetoDTO);
}
