package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class CategoriaSaveDto {

    @NotBlank(message = "El nombre no puede estar en blanco") @Size(max = 250)
    @Valid
    private String nombre;

    @Size(max = 255)
    private String descripcion;

    private EstadoPersona estado;

    public CategoriaSaveDto() {
        estado = EstadoPersona.ACTIVO;
    }



    
}
