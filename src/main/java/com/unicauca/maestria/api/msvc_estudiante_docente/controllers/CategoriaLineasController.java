package com.unicauca.maestria.api.msvc_estudiante_docente.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación.CategoriaLineaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

// @RequiredArgsConstructor
@RestController
@RequestMapping("/api/lineas-investigacion/categoria")
public class CategoriaLineasController {


    @Autowired
    private  CategoriaLineaService categoriaLineaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> crear(@Valid @RequestBody CategoriaSaveDto categoria,
            BindingResult result) {

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaLineaService.Crear(categoria, result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> actualizar(@PathVariable Long id,
            @Valid @RequestBody CategoriaSaveDto categoria, BindingResult result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaLineaService.Actualizar(id, categoria, result));

    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaLineaService.actualizarEstado(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listar() {
        return ResponseEntity.ok(categoriaLineaService.Listar());

    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaResponseDto>> listarPaginado(Pageable page) {
        return ResponseEntity.ok(categoriaLineaService.ListarPaginado(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaLineaService.BuscarPorId(id));
    }

    @GetMapping("/listar/{estado}")
    public ResponseEntity<List<CategoriaResponseDto>> listarCategoriaActivos(@PathVariable String estado) {
        return ResponseEntity.ok(categoriaLineaService.ListarCategoriaActivos(estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFisico(@PathVariable Long id) {
        categoriaLineaService.EliminarFisico(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarLogico(@PathVariable Long id) {
        categoriaLineaService.EliminarLogico(id);
        return ResponseEntity.ok().build();
    }

    // @GetMapping("/obtener/{id}")
    // public ResponseEntity<CategoriaLineasResponseDto>
    // obtenerCategoria(@PathVariable Long id){
    // return ResponseEntity.ok(categoriaLineaService.ObtenerCategoria(id));
    // }

}
