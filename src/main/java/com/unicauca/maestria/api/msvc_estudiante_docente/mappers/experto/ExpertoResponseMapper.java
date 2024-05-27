package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.experto;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.Experto.Experto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.Experto.ExpertoResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.PersonaMapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring",
        uses = {PersonaMapper.class})
public interface ExpertoResponseMapper extends GenericMapper<ExpertoResponseDto, Experto>{
        
        @Override
        @Mapping(target = "lineasInvestigacion",ignore = true)
        ExpertoResponseDto toDto(Experto entity);
    
}
