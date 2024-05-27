package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaSaveDto;




public interface CategoriaLineaService {

    CategoriaResponseDto Crear(CategoriaSaveDto categoria, BindingResult result);
    List<CategoriaResponseDto> Listar();
    Page<CategoriaResponseDto> ListarPaginado(Pageable page);
    CategoriaResponseDto BuscarPorId(Long id);
    CategoriaResponseDto Actualizar(Long id, CategoriaSaveDto catedoriaSaveDto, BindingResult result);
    void actualizarEstado(Long id);
    void EliminarFisico(Long id);
    void EliminarLogico(Long id);
    List<CategoriaResponseDto> ListarCategoriaActivos(String estado);
    CategoriaResponseDto ObtenerCategoria(Long id);
    
}
