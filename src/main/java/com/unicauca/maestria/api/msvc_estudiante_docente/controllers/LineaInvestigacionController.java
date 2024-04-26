package com.unicauca.maestria.api.msvc_estudiante_docente.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.docente.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación.LineaInvestigacionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lineas-investigacion")
public class LineaInvestigacionController {
    private final LineaInvestigacionService lineaInvestigacionService;

    @GetMapping
    public ResponseEntity<List<LineaInvestigacionDto>> listar(){
        return ResponseEntity.ok(lineaInvestigacionService.ListarLineasInvestigacion());
    }
    
}
