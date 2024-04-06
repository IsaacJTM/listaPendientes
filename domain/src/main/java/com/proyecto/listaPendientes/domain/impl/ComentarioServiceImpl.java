package com.proyecto.listaPendientes.domain.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;
import com.proyecto.listaPendientes.domain.port.in.ComentarioServiceIn;
import com.proyecto.listaPendientes.domain.port.out.ComentarioServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioServiceIn {
    private  final ComentarioServiceOut comentarioServiceOut;
    @Override
    public ComentarioDTO crearComentarioIn(RequestComentario requestComentario) throws JsonProcessingException {
        return comentarioServiceOut.crearComentarioOut(requestComentario);
    }

    @Override
    public ComentarioDTO actualzarComentarioIn(Long id, RequestComentario requestComentario) {
        return comentarioServiceOut.actualzarComentarioOut(id, requestComentario);
    }

    @Override
    public ComentarioDTO deleteComentarioIn(Long id) {
        return comentarioServiceOut.deleteComentarioOut(id);
    }
}
