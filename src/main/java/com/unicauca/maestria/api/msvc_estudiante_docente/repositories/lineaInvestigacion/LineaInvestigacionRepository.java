package com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;

public interface LineaInvestigacionRepository extends JpaRepository<LineaInvestigacion, Long> {

    // @Query("SELECT l FROM LineaInvestigacion l WHERE l.categoria.id = ?1 ORDER BY l.titulo ASC")
    // public List<LineaInvestigacion> findAllByCategoriaId(Long idCategoria);
    @Query("SELECT l FROM LineaInvestigacion l WHERE l.categoria.id = ?1 AND l.estado = 'ACTIVO' ORDER BY l.titulo ASC")
    public List<LineaInvestigacion> findAllByCategoriaId(Long idCategoria);
    
    public List<LineaInvestigacion> findAllByEstado(String estado);

    public LineaInvestigacion findByTitulo(String titulo);

    public boolean existsByTitulo(String titulo);

    public Optional<LineaInvestigacion> findById(Long id);

}
