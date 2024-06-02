package com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion;


import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class LineaInvestigacionDto {
	
	private Long id;
	
	private String titulo;

	private String descripcion;
	
	private CategoriaResponseDto categoria;

	private EstadoPersona estado;
}
