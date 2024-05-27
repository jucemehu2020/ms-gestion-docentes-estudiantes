package com.unicauca.maestria.api.msvc_estudiante_docente.repositories.experto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.Experto.ExpertoLineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;

public interface ExpertoLineaInvestigacionRepository extends JpaRepository<ExpertoLineaInvestigacion, Long>{

	// @Query("SELECT e.id FROM ExpertoLineaInvestigacion e where e.experto.id = ?1")
	// public List<Long> findAllByExperto(Long idExperto);
	@Query("SELECT e.lineaInvestigacion.id FROM ExpertoLineaInvestigacion e WHERE e.experto.id = ?1")
	public List<Long> findAllLineaInvestigacionIdsByExperto(Long idExperto);



	@Query("SELECT e.lineaInvestigacion FROM ExpertoLineaInvestigacion e where e.experto.id = ?1")
	public List<LineaInvestigacion> findAllLineasInvByIdExperto(Long idExperto);


	@Modifying
	@Query("DELETE FROM ExpertoLineaInvestigacion e where e.experto.id = ?1 and e.lineaInvestigacion.id = ?2")
	public void deleteByExpertoAndLineaInvestigacion(Long idExperto, Long idLineaInvestigacion);
	
}
