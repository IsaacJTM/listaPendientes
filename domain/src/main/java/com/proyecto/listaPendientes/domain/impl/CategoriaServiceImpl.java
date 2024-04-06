package com.proyecto.listaPendientes.domain.impl;

import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;
import com.proyecto.listaPendientes.domain.port.in.CategoriaServiceIn;
import com.proyecto.listaPendientes.domain.port.out.CategoriaServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaServiceIn {

    private final CategoriaServiceOut categoriaServiceOut;

    @Override
    public CategoriaDTO crearCategoriaIn(RequestCategoria requestCategoria) {
        return categoriaServiceOut.crearCategoriaOut(requestCategoria);
    }

    @Override
    public Optional<CategoriaDTO> obtenerCategoriaIn(Long id) {
        return categoriaServiceOut.obtenerCategoriaOut(id);
    }

    @Override
    public List<CategoriaDTO> obtenerCategoriaAllIn() {
        return categoriaServiceOut.obtenerCategoriaAllOut();
    }

    @Override
    public CategoriaDTO actualzarCategoriaIn(Long id, RequestCategoria requestCategoria) {
        return categoriaServiceOut.actualzarCategoriaOut(id, requestCategoria);
    }

    @Override
    public CategoriaDTO deleteCategoriaIn(Long id) {
        return categoriaServiceOut.deleteCategoriaOut(id);
    }
}
