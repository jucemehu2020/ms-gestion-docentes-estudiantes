package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class LineaInvestigacionSaveDto {

    @NotBlank
    @Size(max = 255)
    private String titulo;
    @NotNull
    private Long idCategoria;

    @Size(max = 255)
    private String descripcion;
    
    private EstadoPersona estado;
    
    public LineaInvestigacionSaveDto() {
        estado = EstadoPersona.ACTIVO;
    }
}
