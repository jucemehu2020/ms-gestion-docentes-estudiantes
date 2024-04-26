package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.docente.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.repositories.LineaInvestigacionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LineaInvestigacionServiceImpl implements LineaInvestigacionService{

    private final LineaInvestigacionRepository lineaInvestigacionRepository;


    @Override
    @Transactional(readOnly = true)
    public List<LineaInvestigacionDto> ListarLineasInvestigacion(){
        
        List<LineaInvestigacionDto> lineasInvestigacion = lineaInvestigacionRepository.findAll()
        .stream()
        .map(linea -> new LineaInvestigacionDto(linea.getId(), linea.getTitulo(), linea.getCategoria())).toList();
        return lineasInvestigacion;
    }
    
}
