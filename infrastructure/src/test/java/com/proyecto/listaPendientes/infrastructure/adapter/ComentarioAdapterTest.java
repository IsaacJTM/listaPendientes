package com.proyecto.listaPendientes.infrastructure.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;
import com.proyecto.listaPendientes.infrastructure.mapper.ComentarioMapper;
import com.proyecto.listaPendientes.infrastructure.repository.ComentarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ComentarioAdapterTest {
    @Mock
    private ComentarioMapper comentarioMapper;
    @Mock
    private ComentarioRepository comentarioRepository;

    @InjectMocks
    private ComentarioAdapter comentarioAdapter;


    @Test
    void TestCrearComentarioOut() throws JsonProcessingException {
        //Datos de entrda
        RequestComentario comentario = new RequestComentario("Comentario");
        ComentarioDTO comentarioDTOEsperado = getComentario();

        //Comportamiento
        when(comentarioMapper.mapToDTO(any())).thenReturn(comentarioDTOEsperado);

        ComentarioDTO comentarioDTORecibido = comentarioAdapter.crearComentarioOut(comentario);
        // Verificando resultado
        assertEquals(comentarioDTOEsperado.getIdComentario(), comentarioDTORecibido.getIdComentario());
        assertEquals(comentarioDTOEsperado.getIdComentario(), comentarioDTORecibido.getIdComentario());
        assertEquals(comentarioDTOEsperado.getEstadoComentario(), comentarioDTORecibido.getEstadoComentario());
    }





    private ComentarioDTO getComentario(){
        return new ComentarioDTO(1L,getTimestamp(), getTimestamp(),
                "Comentario", 1,"admin",getTimestamp(),"admin",getTimestamp(),
                "admin", getTimestamp(),"Mensaje");
    }


    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}