package com.unicauca.maestria.api.msvc_estudiante_docente.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.unicauca.maestria.api.msvc_estudiante_docente.domain.ExpertoLineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.LineaInvestigacion;

public interface ExpertoLineaInvestigacionRepository extends JpaRepository<ExpertoLineaInvestigacion, Long>{

	// @Query("SELECT e.id FROM ExpertoLineaInvestigacion e where e.experto.id = ?1")
	// public List<Long> findAllByExperto(Long idExperto);
	@Query("SELECT e.lineaInvestigacion.id FROM ExpertoLineaInvestigacion e WHERE e.experto.id = ?1")
	public List<Long> findAllLineaInvestigacionIdsByExperto(Long idExperto);



	@Query("SELECT e.lineaInvestigacion FROM ExpertoLineaInvestigacion e where e.experto.id = ?1")
	public List<LineaInvestigacion> findAllLineasInvByIdExperto(Long idExperto);
}
