package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InjectionStrategy;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface LineaInvestigacionSaveMapper extends GenericMapper<LineaInvestigacionSaveDto, LineaInvestigacion>{

    @Override
    @Mapping(target ="id", ignore = true)
    @Mapping(source = "idCategoria",target="categoria", ignore = true)
    LineaInvestigacion toEntity(LineaInvestigacionSaveDto dto);

    @Override
    // @Mapping(source = "id", ignore = true)
    @Mapping(source = "categoria.id",target="idCategoria")
    LineaInvestigacionSaveDto toDto(LineaInvestigacion entity);

    @Override
    List<LineaInvestigacion> toEntityList(List<LineaInvestigacionSaveDto> dtos);

    @Override
    List<LineaInvestigacionSaveDto> toDtoList(List<LineaInvestigacion> entities);
}
