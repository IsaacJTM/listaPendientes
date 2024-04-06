package com.proyecto.listaPendientes.domain.port.out;

import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaServiceOut  {
    CategoriaDTO crearCategoriaOut(RequestCategoria requestCategoria);
    Optional<CategoriaDTO> obtenerCategoriaOut(Long id);
    List<CategoriaDTO> obtenerCategoriaAllOut();
    CategoriaDTO actualzarCategoriaOut(Long id, RequestCategoria requestCategoria);
    CategoriaDTO deleteCategoriaOut(Long id);
}
