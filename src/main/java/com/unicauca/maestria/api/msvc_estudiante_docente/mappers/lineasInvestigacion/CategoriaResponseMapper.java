package com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.GenericMapper;

import org.mapstruct.Mapping; // Add this import statement
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper( componentModel = "spring", uses = {
                LineaInvestigacionMapper.class })
public interface  CategoriaResponseMapper extends GenericMapper<CategoriaResponseDto, CategoriaLinea> {

       
        // private LineaInvestigacionMapper lineaInvestigacionMapper;

        // @Override
        // CategoriaLinea toEntity(CategoriaResponseDto dto);

        @Override
        @Mapping(target = "lineasInvestigacion", ignore = true)
         CategoriaResponseDto toDto(CategoriaLinea entity) ;
        //  {
        //         if (entity == null) {
        //                 return null;
        //         }

        //         CategoriaResponseDto dto = new CategoriaResponseDto();
        //         dto.setId(entity.getId());
        //         dto.setNombre(entity.getNombre());
        //         dto.setEstado(entity.getEstado());
        //         // establecer otros campos aquí...

        //         return dto;
        // }

        // @Override
        // List<CategoriaLinea> toEntityList(List<CategoriaResponseDto> dtos);

        // @Override
        // List<CategoriaResponseDto> toDtoList(List<CategoriaLinea> entities);

        // @Override
        // @Mapping(target = "lineasInvestigacion", ignore = true)
        // CategoriaResponseDto toDto(CategoriaLinea entity);

}
