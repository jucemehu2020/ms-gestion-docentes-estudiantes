package com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;

public interface CategoriaLineasRepository extends JpaRepository<CategoriaLinea, Long> {

    public CategoriaLinea findByNombre(String nombre);

    public boolean existsByNombre(String nombre);

    @Query("SELECT c FROM CategoriaLinea c WHERE c.estado = ?1 ORDER BY c.nombre")
    public List<CategoriaLinea> findAllByEstado(EstadoPersona estado);


    public Optional<CategoriaLinea> findById(Long id);
}
