package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.Experto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.common.PersonaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class ExpertoSaveDto {

    @NotNull
    @Valid
    private PersonaDto persona;


    @NotBlank @Size(max = 50)
    private String tituloexper;
    @NotBlank @Size(max = 50)
    private String universidadtitexp;
    // @NotBlank @Size(max = 30)
    // private String copiadocidentidad;
    @NotBlank @Size(max = 50)
    private String universidadexp;
    @NotBlank @Size(max = 50)
    private String facultadexp;
    @NotBlank @Size(max = 50)
    private String grupoinvexp;

    @NotEmpty
    private List<Long> idsLineaInvestigacion;

    @NotBlank @Size(max = 50)
    private String observacionexp;

    public ExpertoSaveDto() {
        idsLineaInvestigacion = new ArrayList<>();
        
    }

    
}
