package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionSaveDto;

public interface LineaInvestigacionService {
        
    List<LineaInvestigacionDto> Listar();

    Page<LineaInvestigacionDto> ListarPaginado(Pageable page);

    LineaInvestigacionDto CrearLineaInvestigacion(LineaInvestigacionSaveDto lineaInvestigacionSaveDto,
            BindingResult result);

    LineaInvestigacionDto ActualizarLineaInvestigacion(Long id, LineaInvestigacionSaveDto lineaInvestigacionSaveDto,
            BindingResult result);

    String ActualizarEstado(Long id);

    LineaInvestigacionDto BuscarPorId(Long id);

    void EliminarFisico(Long id);

    void EliminarLogico(Long id);

    List<LineaInvestigacionDto> ListarEstado(String estado);

}
