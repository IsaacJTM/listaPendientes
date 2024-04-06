package com.proyecto.listaPendientes.infrastructure.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;
import com.proyecto.listaPendientes.domain.port.out.ComentarioServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.ComentarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.ComentarioMapper;
import com.proyecto.listaPendientes.infrastructure.repository.ComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComentarioAdapter implements ComentarioServiceOut {
    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepository comentarioRepository;



    @Override
    public ComentarioDTO crearComentarioOut(RequestComentario requestComentario) throws JsonProcessingException {
        try {
            ComentarioEntity entity = new ComentarioEntity();
            entity.setDescripcionComentario(requestComentario.getDescripcionComentario());
            entity.setFechaCreacion(getTimestamp());
            comentarioRepository.save(entity);
            return comentarioMapper.mapToDTO(entity);
        }catch (Exception ex){
            ComentarioDTO comentarioDTO = new ComentarioDTO();
            comentarioDTO.setMensaje("Error" + ex.getMessage());
            return comentarioDTO;
        }
    }

    @Override
    public ComentarioDTO actualzarComentarioOut(Long id, RequestComentario requestComentario) {
        boolean existe = comentarioRepository.existsById(id);
        if(existe){
            Optional<ComentarioEntity> entity = comentarioRepository.findById(id);
            entity.get().setDescripcionComentario(requestComentario.getDescripcionComentario());
            entity.get().setFechaActualizacion(getTimestamp());
            comentarioRepository.save(entity.get());
            return comentarioMapper.mapToDTO(entity.get());
        }
        return null;
    }

    @Override
    public ComentarioDTO deleteComentarioOut(Long id) {
        boolean existe = comentarioRepository.existsById(id);
        if(existe){
            Optional<ComentarioEntity> entity = comentarioRepository.findById(id);
            comentarioRepository.save(entity.get());
            return comentarioMapper.mapToDTO(entity.get());
        }
        return null;
    }


    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
