package com.unicauca.maestria.api.msvc_estudiante_docente.services.LineaInvestigación;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.unicauca.maestria.api.msvc_estudiante_docente.common.enums.EstadoPersona;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.CategoriaLinea;
import com.unicauca.maestria.api.msvc_estudiante_docente.domain.lineaInvestigacion.LineaInvestigacion;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CamposUnicosCategoriaDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaResponseDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.CategoriaSaveDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.dtos.lineaInvestigacion.LineaInvestigacionDto;
import com.unicauca.maestria.api.msvc_estudiante_docente.exceptions.FieldErrorException;
import com.unicauca.maestria.api.msvc_estudiante_docente.exceptions.FieldUniqueException;
import com.unicauca.maestria.api.msvc_estudiante_docente.exceptions.ResourceNotFoundException;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.CategoriaResponseMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.CategoriaSaveMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.mappers.lineasInvestigacion.LineaInvestigacionMapper;
import com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion.CategoriaLineasRepository;
import com.unicauca.maestria.api.msvc_estudiante_docente.repositories.lineaInvestigacion.LineaInvestigacionRepository;

import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
@Service
public class CategoriaLineaServiceImpl implements CategoriaLineaService {

    @Autowired
    private  InformacionUnicaCategoria informacionUnicaCategoria;
    @Lazy
    @Autowired
    private  CategoriaLineasRepository categoriaRepository;
    @Lazy
    @Autowired
    private  CategoriaResponseMapper categoriaResponseMapper;
    @Lazy
    @Autowired
    private  CategoriaSaveMapper categoriaSaveMapper;
    @Lazy
    @Autowired
    private  LineaInvestigacionRepository lineaInvestigacionRepository;
    @Lazy
    @Autowired
    private  LineaInvestigacionMapper lineaInvestigacionMapper;

    // @Autowired
    // public CategoriaLineaServiceImpl(
    //         CategoriaLineasRepository categoriaRepository,
    //         CategoriaSaveMapper categoriaSaveMapper,
    //         @Lazy CategoriaResponseMapper categoriaResponseMapper,
    //         InformacionUnicaCategoria informacionUnicaCategoria,
    //         @Lazy LineaInvestigacionMapper lineaInvestigacionMapper,
    //         LineaInvestigacionRepository lineaInvestigacionRepository) {
    //     this.categoriaRepository = categoriaRepository;
    //     this.categoriaSaveMapper = categoriaSaveMapper;
    //     this.categoriaResponseMapper = categoriaResponseMapper;
    //     this.informacionUnicaCategoria = informacionUnicaCategoria;
    //     this.lineaInvestigacionMapper = lineaInvestigacionMapper;
    //     this.lineaInvestigacionRepository = lineaInvestigacionRepository;
    // }

    @Override
    @Transactional
    public CategoriaResponseDto Crear(CategoriaSaveDto categoria, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }

        Map<String, String> validacionCamposUnicos = validacionCampoUnicos(obtenerCamposUnicos(categoria), null);
        if (!validacionCamposUnicos.isEmpty()) {
            throw new FieldUniqueException(validacionCamposUnicos);
        }

        // CategoriaLinea categoriaLinea =
        // categoriaSaveMapper.toEntity(categoria);
        CategoriaLinea categoriaLinea = categoriaRepository
                .save(categoriaSaveMapper.toEntity(categoria));

        return categoriaResponseMapper.toDto(categoriaLinea);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDto> Listar() {
       
       return categoriaRepository.findAll()
                .stream()
                .map(this::crearCategoriaResposeDto)
                .toList();
      
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoriaResponseDto> ListarPaginado(Pageable page) {
        return categoriaRepository.findAll(page)
                .map(this::crearCategoriaResposeDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDto BuscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(this::crearCategoriaResposeDto)
                .orElseThrow(() -> new ValidationException("No se encontró la categoria con el id: " + id));
    }

    @Override
    @Transactional
    public CategoriaResponseDto Actualizar(Long id, CategoriaSaveDto categoriaSaveDto,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }

        CategoriaLinea categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ValidationException("No se encontró la categoria con el id: " + id));

        Map<String, String> validacionCamposUnicos = validacionCampoUnicos(obtenerCamposUnicos(categoriaSaveDto),
                obtenerCamposUnicos(categoriaSaveMapper.toDto(categoria)));

        if (!validacionCamposUnicos.isEmpty()) {
            throw new FieldUniqueException(validacionCamposUnicos);
        }

        categoria = categoriaSaveMapper.toEntity(categoriaSaveDto);
        categoria.setId(id);
        categoria = categoriaRepository.save(categoria);

        // return crearCategoriaResposeDto(categoria);

        return categoriaResponseMapper.toDto(categoria);
    }

    @Override
    @Transactional
    public void actualizarEstado(Long id) {
        CategoriaLinea categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoria con el id: "));

        EstadoPersona estado = categoria.getEstado() == EstadoPersona.ACTIVO ? EstadoPersona.INACTIVO : EstadoPersona.ACTIVO;
        categoria.setEstado(estado);
        categoriaRepository.save(categoria);
    }

    // private Boolean ValidarCategoria(@Valid CategoriaLineasSaveDto
    // categoriaLineasSaveDto) {

    // BindingResult result = new BeanPropertyBindingResult(categoriaLineasSaveDto,
    // "categoriaLineasSaveDto");

    // validator.validate(categoriaLineasSaveDto, result);
    // return result.hasErrors();

    // }

    @Override
    @Transactional
    public void EliminarFisico(Long id) {
        CategoriaLinea categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoria con el id: "));
        categoriaRepository.delete(categoria);
    }

    @Override
    @Transactional
    public void EliminarLogico(Long id) {
        CategoriaLinea categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoria con el id: "));
        categoria.setEstado(EstadoPersona.INACTIVO);
        categoriaRepository.save(categoria);
    }

    private Map<String, String> validacionCampoUnicos(CamposUnicosCategoriaDto camposUnicos,
            CamposUnicosCategoriaDto camposUnicosBD) {

        Map<String, Function<CamposUnicosCategoriaDto, Boolean>> mapCamposUnicos = new HashMap<>();

        mapCamposUnicos.put("nombre",
                dto -> (camposUnicosBD == null || !dto.getNombre().equals(camposUnicosBD.getNombre()))
                        && categoriaRepository.existsByNombre(dto.getNombre()));

        Predicate<Field> existeCampoUnico = campo -> mapCamposUnicos.containsKey(campo.getName());
        Predicate<Field> existeCampoBD = campoBD -> mapCamposUnicos.get(campoBD.getName()).apply(camposUnicos);
        Predicate<Field> campoInvalido = existeCampoUnico.and(existeCampoBD);

        return Arrays.stream(camposUnicos.getClass().getDeclaredFields())
                .filter(campoInvalido)
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toMap(Field::getName, field -> {
                    Object valorCampo = null;
                    try {
                        valorCampo = field.get(camposUnicos);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return mensajeException(field.getName(), valorCampo);
                }));

    }

    private <T> String mensajeException(String nombreCampo, T valorCampo) {
        return "Campo único, ya existe una categoria con la información: " + nombreCampo + ": " + valorCampo;
    }

    private CamposUnicosCategoriaDto obtenerCamposUnicos(CategoriaSaveDto categoriaSaveDto) {
        return informacionUnicaCategoria.apply(categoriaSaveDto);
    }

    private CategoriaResponseDto crearCategoriaResposeDto(CategoriaLinea categoria) {
        List<LineaInvestigacion> lineaInvestigacion = lineaInvestigacionRepository.findAllByCategoriaId(categoria.getId());

        List<LineaInvestigacionDto> lineaInvestigacionDto= lineaInvestigacionMapper.toDtoList(lineaInvestigacion);

        CategoriaResponseDto categoriaResponseDto = categoriaResponseMapper.toDto(categoria);
        categoriaResponseDto.setLineasInvestigacion(lineaInvestigacionDto);
        return categoriaResponseDto;
    }

    // private void actualizarinformacionCategoria(CategoriaLineaInvestigacion
    // categoria,
    // CategoriaLineaInvestigacion categoriaBD) {

    // // falta agregar id de categoria
    // categoriaBD.setNombre(categoria.getNombre());
    // categoriaBD.setEstado(categoria.getEstado());

    // }

    @Override
    public List<CategoriaResponseDto> ListarCategoriaActivos(String estado) {
        List<CategoriaLinea> categoriasActivas = categoriaRepository
                .findAllByEstado(EstadoPersona.valueOf(estado));
        return categoriasActivas.stream().map(this::crearCategoriaResposeDto).toList();
    }

    @Override
    public CategoriaResponseDto ObtenerCategoria(Long id) {
        CategoriaLinea categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoria con el id: "));
        return crearCategoriaResposeDto(categoria);
    }

}
