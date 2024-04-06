package com.proyecto.listaPendientes.application.controller;

import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;
import com.proyecto.listaPendientes.domain.port.in.CategoriaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/v1/admin/Categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaServiceIn categoriaServiceIn;
    @PostMapping("Crear")
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody RequestCategoria requestCategoria){
        return ResponseEntity.ok(categoriaServiceIn.crearCategoriaIn(requestCategoria));
    }

    @GetMapping("Obtener/{id}")
    public ResponseEntity<Optional> obtenerCategoria(@PathVariable Long id){
        return ResponseEntity.ok(categoriaServiceIn.obtenerCategoriaIn(id));
    }
    @GetMapping("allObtener")
    public ResponseEntity<List<CategoriaDTO>> obtenerCategoriaAllIn(){
        return ResponseEntity.ok(categoriaServiceIn.obtenerCategoriaAllIn());
    }

    @PutMapping("Actualizar/{id}")
    public  ResponseEntity<CategoriaDTO> actualzarCategoria(@PathVariable Long id,@RequestBody RequestCategoria requestCategoria){
        return ResponseEntity.ok(categoriaServiceIn.actualzarCategoriaIn(id,requestCategoria));
    }

    @DeleteMapping("Eliminar/{id}")
    public ResponseEntity<CategoriaDTO> deleteCategoria(@PathVariable Long id){
        return ResponseEntity.ok(categoriaServiceIn.deleteCategoriaIn(id));
    }
}
