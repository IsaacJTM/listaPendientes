package com.proyecto.listaPendientes.domain.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;

public interface ComentarioServiceOut {
    ComentarioDTO crearComentarioOut(RequestComentario requestComentario) throws JsonProcessingException;

    ComentarioDTO actualzarComentarioOut(Long id, RequestComentario requestComentario);
    ComentarioDTO deleteComentarioOut(Long id);
}
