package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CamposUnicosCategoriaDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaSaveDto;

@Service
public class InformacionUnicaCategoria implements Function<CategoriaSaveDto, CamposUnicosCategoriaDto>{

    @Override
    public CamposUnicosCategoriaDto apply(CategoriaSaveDto categoriaSaveDto) {
        
        return CamposUnicosCategoriaDto.builder()
                .nombre(categoriaSaveDto.getNombre())
                .build();
    }
    
}
