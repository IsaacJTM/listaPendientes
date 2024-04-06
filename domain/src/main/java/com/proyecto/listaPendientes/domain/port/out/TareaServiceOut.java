package com.proyecto.listaPendientes.domain.port.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestTarea;

import java.util.List;
import java.util.Optional;

public interface TareaServiceOut {

    TareaDTO crearTareaOut(RequestTarea requestTarea) throws JsonProcessingException;
    Optional<TareaDTO> obtenerTareaOut(Long id);
    List<TareaDTO> obtenerTodasOut();
    TareaDTO actualzarTareaOut(Long id, RequestTarea requestTarea);
    TareaDTO deleteTareaOut(Long id);

}
