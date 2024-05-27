package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, componentModel = "spring")
public interface CategoriaSaveMapper extends GenericMapper<CategoriaSaveDto, CategoriaLinea> {
   
    @Override
    CategoriaLinea toEntity(CategoriaSaveDto dto);

    @Override
    CategoriaSaveDto toDto(CategoriaLinea entity);

    @Override
    List<CategoriaLinea> toEntityList(List<CategoriaSaveDto> dtos);

    @Override
    List<CategoriaSaveDto> toDtoList(List<CategoriaLinea> entities);

}
