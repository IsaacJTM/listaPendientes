package com.proyecto.listaPendientes.application.controller;

import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestTarea;
import com.proyecto.listaPendientes.domain.port.in.TareaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/Users")
@RequiredArgsConstructor
public class TareaController {

    private final TareaServiceIn tareaServiceIn;

    @GetMapping("delegante/CrearTarea")
    public ResponseEntity<TareaDTO> crearTarea(@RequestBody RequestTarea requestTarea){
        return ResponseEntity.ok(tareaServiceIn.crearTareaIn(requestTarea));
    }

    @GetMapping("delegante/ObtenerTarea/{id}")
    public ResponseEntity<Optional<TareaDTO>> obtenerTarea(@PathVariable Long id){
        return ResponseEntity.ok(tareaServiceIn.obtenerTareaIn(id));
    }
    @GetMapping("delegante/AllTarea")
    public ResponseEntity<List<TareaDTO>> obtenerTodasDelegante(){
        return ResponseEntity.ok(tareaServiceIn.obtenerTodasIn());
    }

    @GetMapping("responsable/AllTarea")
    public ResponseEntity<List<TareaDTO>> obtenerTodasResponsable(){
        return ResponseEntity.ok(tareaServiceIn.obtenerTodasIn());
    }

    @PutMapping("delegante/ActualizarTarea/{id}")
    public ResponseEntity<TareaDTO> actualzarTarea(@PathVariable Long id,@RequestBody RequestTarea requestTarea){
        return ResponseEntity.ok(tareaServiceIn.actualzarTareaIn(id,requestTarea));
    }

    @DeleteMapping("delegante/EliminarTarea/{id}")
    public ResponseEntity<TareaDTO> deleteTarea(@PathVariable Long id){
        return ResponseEntity.ok(tareaServiceIn.deleteTareaIn(id));
    }
}
