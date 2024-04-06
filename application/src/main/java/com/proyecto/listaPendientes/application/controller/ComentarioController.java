package com.proyecto.listaPendientes.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.dto.ComentarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestComentario;
import com.proyecto.listaPendientes.domain.port.in.ComentarioServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioServiceIn comentarioServiceIn;

    @PostMapping("/delegante/CrearTarea")
    public ResponseEntity<ComentarioDTO> crearComentario(@RequestBody RequestComentario requestComentario) throws JsonProcessingException {
        return ResponseEntity.ok(comentarioServiceIn.crearComentarioIn(requestComentario));
    }
    @PutMapping("/delegante/ActualizarTarea/{id}")
    public ResponseEntity<ComentarioDTO> actualzarTarea(@PathVariable Long id,@RequestBody RequestComentario requestComentario){
        return ResponseEntity.ok(comentarioServiceIn.actualzarComentarioIn(id,requestComentario));
    }

    @DeleteMapping("/delegante/EliminarTarea/{id}")
    public ResponseEntity<ComentarioDTO> deleteTarea(@PathVariable Long id){
        return ResponseEntity.ok(comentarioServiceIn.deleteComentarioIn(id));
    }

}
