package com.proyecto.listaPendientes.domain.port.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestTarea;


public interface ComentarioServiceIn {

    ComentarioDTO crearComentarioIn(RequestComentario requestComentario) throws JsonProcessingException;

    ComentarioDTO actualzarComentarioIn(Long id, RequestComentario requestComentario);
    ComentarioDTO deleteComentarioIn(Long id);
}
