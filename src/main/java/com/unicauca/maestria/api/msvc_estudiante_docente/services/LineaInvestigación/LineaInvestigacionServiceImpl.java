package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.exceptions.FieldErrorException;
import com.unicauca.maestria.api.msvc_estudiante_docente.exceptions.ResourceNotFoundException;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.CategoriaResponseMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.LineaInvestigacionMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.LineaInvestigacionSaveMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion.CategoriaLineasRepository;
import com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion.LineaInvestigacionRepository;

import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Service
public class LineaInvestigacionServiceImpl implements LineaInvestigacionService {

    @Lazy
    @Autowired
    private  LineaInvestigacionRepository lineaInvestigacionRepository;
    @Lazy
    @Autowired
    private  LineaInvestigacionMapper lineaInvestigacionMapper;
    @Lazy
    @Autowired
    private  LineaInvestigacionSaveMapper lineaInvestigacionSaveMapper;
    @Lazy
    @Autowired
    private  CategoriaResponseMapper categoriaLineasResponseMapper;
    @Lazy
    @Autowired
    private  CategoriaLineasRepository categoriaLineaRepository;

    // @Autowired
    // public LineaInvestigacionServiceImpl(
    //         LineaInvestigacionRepository lineaInvestigacionRepository,
    //         LineaInvestigacionSaveMapper lineaInvestigacionSaveMapper,
    //         @Lazy LineaInvestigacionMapper lineaInvestigacionResponseMapper,
    //         CategoriaLineasRepository categoriaRepository,
    //         @Lazy CategoriaResponseMapper categoriaLineasResponseMapper) {
    //     this.lineaInvestigacionRepository = lineaInvestigacionRepository;
    //     this.lineaInvestigacionSaveMapper = lineaInvestigacionSaveMapper;
    //     this.lineaInvestigacionMapper = lineaInvestigacionResponseMapper;
    //     this.categoriaLineaRepository = categoriaRepository;
    //     this.categoriaLineasResponseMapper = categoriaLineasResponseMapper;
    // }

    @Override
    @Transactional
    public LineaInvestigacionDto CrearLineaInvestigacion(LineaInvestigacionSaveDto lineaInvestigacionSaveDto,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }
        // crearLineaSaveDto(lineaInvestigacionSaveDto);
        LineaInvestigacion linea = crearLineaSaveDto(lineaInvestigacionSaveDto);

        LineaInvestigacion lineainvestigacion = lineaInvestigacionRepository
                .save(linea);
        return lineaInvestigacionMapper.toDto(lineainvestigacion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LineaInvestigacionDto> Listar() {
        List<LineaInvestigacion> lineas = lineaInvestigacionRepository.findAll();

        return lineaInvestigacionMapper.toDtoList(lineas);
    }

    @Override
    @Transactional(readOnly = true)

    public Page<LineaInvestigacionDto> ListarPaginado(Pageable page) {
        Page<LineaInvestigacion> lineas = lineaInvestigacionRepository.findAll(page);
        return lineaInvestigacionMapper.toDtoPage(lineas);
    }

    @Override
    @Transactional(readOnly = true)
    public LineaInvestigacionDto BuscarPorId(Long id) {
        LineaInvestigacion linea = lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigación con " + id + "no encontrado"));

        return lineaInvestigacionMapper.toDto(linea);
    }

    @Override
    @Transactional
    public LineaInvestigacionDto ActualizarLineaInvestigacion(Long id,
            LineaInvestigacionSaveDto lineaInvestigacionSaveDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }
        LineaInvestigacion linea = lineaInvestigacionSaveMapper.toEntity(lineaInvestigacionSaveDto);
        LineaInvestigacion lineaBD = lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigación no encontrada"));

        lineaBD.setTitulo(linea.getTitulo());
        lineaBD.setCategoria(linea.getCategoria());
        LineaInvestigacion lineaSave = lineaInvestigacionRepository.save(lineaBD);
        return lineaInvestigacionMapper.toDto(lineaSave);
    }

    @Override
    @Transactional
    public void ActualizarEstado(Long id) {
        LineaInvestigacion linea = lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigación no encontrada"));
        EstadoPersona estado = linea.getEstado() == EstadoPersona.ACTIVO ? EstadoPersona.INACTIVO : EstadoPersona.ACTIVO;
        linea.setEstado(estado);
        lineaInvestigacionRepository.save(linea);
    }

    @Override
    @Transactional
    public void EliminarFisico(Long id) {
        LineaInvestigacion linea = lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigación no encontrada"));
        lineaInvestigacionRepository.delete(linea);
    }

    @Override
    @Transactional
    public void EliminarLogico(Long id) {
        LineaInvestigacion linea = lineaInvestigacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linea de investigación no encontrada"));
        linea.setEstado(EstadoPersona.INACTIVO);
        lineaInvestigacionRepository.save(linea);

    }

    private LineaInvestigacionDto crearLineaResposeDto(LineaInvestigacion lineaInvestigacion, Long idCategoria) {

        CategoriaLinea categoria = categoriaLineaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de linea de investigación no encontrada"));

        LineaInvestigacionDto lineaInvestigacionDto = lineaInvestigacionMapper.toDto(lineaInvestigacion);
        CategoriaResponseDto categoriaDto = categoriaLineasResponseMapper.toDto(categoria);
        lineaInvestigacionDto.setCategoria(categoriaDto);
        return lineaInvestigacionDto;

    }

    private LineaInvestigacion crearLineaSaveDto(LineaInvestigacionSaveDto lineaInvestigacionSaveDto) {
        CategoriaLinea categoria = categoriaLineaRepository.findById(lineaInvestigacionSaveDto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de linea de investigación no encontrada"));

        LineaInvestigacion linea = lineaInvestigacionSaveMapper.toEntity(lineaInvestigacionSaveDto);
        linea.setCategoria(categoria);
        return linea;
    }

    @Override
    public List<LineaInvestigacionDto> ListarEstado(String estado) {
        List<LineaInvestigacion> lineas = lineaInvestigacionRepository.findAllByEstado(estado);

        return lineaInvestigacionMapper.toDtoList(lineas);

    }
}