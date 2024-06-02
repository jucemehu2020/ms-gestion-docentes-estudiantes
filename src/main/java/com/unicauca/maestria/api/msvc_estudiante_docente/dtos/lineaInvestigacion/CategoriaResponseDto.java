package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion;

import java.util.ArrayList;
import java.util.List;



import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class CategoriaResponseDto {

    private String nombre;
    private Long id;
    private String descripcion;
    private EstadoPersona estado;
    private List<LineaInvestigacionDto> lineasInvestigacion;

    public CategoriaResponseDto() {
        lineasInvestigacion = new ArrayList<>();
    }

}
