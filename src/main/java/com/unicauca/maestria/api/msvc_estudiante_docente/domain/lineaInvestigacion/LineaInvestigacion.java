package com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion;

import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import lombok.AllArgsConstructor;
// import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "lineas_investigacion")
public class LineaInvestigacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private CategoriaLinea categoria;

	@Enumerated(EnumType.STRING)
	private EstadoPersona estado;

	public LineaInvestigacion() {
		estado = EstadoPersona.ACTIVO;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaInvestigacion other = (LineaInvestigacion) obj;
		return Objects.equals(id, other.id);
	}

}
