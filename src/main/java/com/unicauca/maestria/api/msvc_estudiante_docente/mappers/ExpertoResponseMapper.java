package com.unicauca.maestria.api.msvc_estudiante_docente.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.Experto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.Experto.ExpertoResponseDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {PersonaMapper.class})
public interface ExpertoResponseMapper extends GenericMapper<ExpertoResponseDto, Experto>{
        
        @Override
        @Mapping(target = "lineasInvestigacion",ignore = true)
        ExpertoResponseDto toDto(Experto entity);
    
}
