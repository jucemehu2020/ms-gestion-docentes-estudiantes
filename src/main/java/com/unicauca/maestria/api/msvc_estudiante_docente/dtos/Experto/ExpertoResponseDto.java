package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.Experto;

import java.util.ArrayList;
import java.util.List;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.common.PersonaDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.docente.LineaInvestigacionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class ExpertoResponseDto {

    private Long id;
    private PersonaDto persona;
    private String tituloexper;
    private String universidadtitexp;
    private String copiadocidentidad;
    private String universidadexp;
    private String facultadexp;
    private String grupoinvexp;
    private List<LineaInvestigacionDto> lineasInvestigacion;
    private String observacionexp;
    private EstadoPersona estado;

    public ExpertoResponseDto() {
        lineasInvestigacion =new ArrayList<>();
    }

    
}
