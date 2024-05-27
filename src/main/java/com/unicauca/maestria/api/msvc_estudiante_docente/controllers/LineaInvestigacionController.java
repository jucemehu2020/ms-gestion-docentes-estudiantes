package com.unicauca.maestria.api.msvc_estudiante_docente.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación.LineaInvestigacionService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



// @RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/lineas-investigacion")
public class LineaInvestigacionController {
    @Autowired
    private  LineaInvestigacionService lineaInvestigacionService;

    // @Autowired
    // public LineaInvestigacionController(@Lazy LineaInvestigacionService lineaInvestigacionService) {
    //     this.lineaInvestigacionService = lineaInvestigacionService;
    // }

    @PostMapping
    public ResponseEntity<LineaInvestigacionDto> Crear( @Valid @RequestBody LineaInvestigacionSaveDto LineaInvestigacion,BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(lineaInvestigacionService.CrearLineaInvestigacion(LineaInvestigacion,result));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<LineaInvestigacionDto> Actualizar(@PathVariable Long id, @Valid @RequestBody LineaInvestigacionSaveDto LineaInvestigacion,BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lineaInvestigacionService.ActualizarLineaInvestigacion(id, LineaInvestigacion,result));
        
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> ActualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(lineaInvestigacionService.ActualizarEstado(id));
    }

    @GetMapping
    public ResponseEntity<List<LineaInvestigacionDto>> listar(){
        return ResponseEntity.ok(lineaInvestigacionService.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LineaInvestigacionDto> BuscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(lineaInvestigacionService.BuscarPorId(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<LineaInvestigacionDto>> ListarPaginado(Pageable page){
        return ResponseEntity.ok(lineaInvestigacionService.ListarPaginado(page));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliminarFisico(@PathVariable Long id){
        lineaInvestigacionService.EliminarFisico(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> EliminarLogico(@PathVariable Long id){
        lineaInvestigacionService.EliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar/{estado}")
    public ResponseEntity<List<LineaInvestigacionDto>> ListarEstado(@PathVariable String estado){
        return ResponseEntity.ok(lineaInvestigacionService.ListarEstado(estado));
    }

    
}
