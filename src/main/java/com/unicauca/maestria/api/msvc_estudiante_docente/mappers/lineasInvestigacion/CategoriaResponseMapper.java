package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;

import org.mapstruct.Mapping; // Add this import statement


@Mapper( componentModel = "spring", uses = {
                LineaInvestigacionMapper.class })
public interface  CategoriaResponseMapper extends GenericMapper<CategoriaResponseDto, CategoriaLinea> {

       

        @Override
        @Mapping(target = "lineasInvestigacion", ignore = true)
         CategoriaResponseDto toDto(CategoriaLinea entity) ;
       

}
