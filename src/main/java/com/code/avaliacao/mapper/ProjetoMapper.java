package com.code.avaliacao.mapper;

import com.code.avaliacao.dto.ProjetoDTO;
import com.code.avaliacao.model.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {
    ProjetoMapper MAPPER = Mappers.getMapper(ProjetoMapper.class);

    ProjetoDTO toDTO(Projeto projeto);

    Projeto toEntity(ProjetoDTO projetoDTO);
}
