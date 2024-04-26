package com.unicauca.maestria.api.msvc_estudiante_docente.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.LineaInvestigacion;

public interface LineaInvestigacionRepository extends JpaRepository<LineaInvestigacion, Long>{

    List<LineaInvestigacion> findAll();
}
