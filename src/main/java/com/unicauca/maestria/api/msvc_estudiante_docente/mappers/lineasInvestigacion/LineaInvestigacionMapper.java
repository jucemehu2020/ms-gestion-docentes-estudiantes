package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;

@Mapper( componentModel = "spring", uses = {CategoriaResponseMapper.class})
public interface LineaInvestigacionMapper extends GenericMapper<LineaInvestigacionDto, LineaInvestigacion> {

    // @Lazy
    // @Autowired
    // private  CategoriaResponseMapper categoriaResponseMapper;
    @Override
    @Mapping(target = "categoria", ignore = true)
    LineaInvestigacionDto toDto(LineaInvestigacion entity);
}
