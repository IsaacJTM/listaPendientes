package com.proyecto.listaPendientes.application.controller;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestUsuario;
import com.proyecto.listaPendientes.domain.port.in.UsuarioServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("api/v1")
@RequiredArgsConstructor


public class UsuarioController {
    private final UsuarioServiceIn usuarioServiceIn;

    @GetMapping("Users/responsable/ObtenerUser/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioResponsable(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioServiceIn.getUsuarioIn(id).get());
    }


    @GetMapping("Users/delegante/ObtenerUser/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioDelegate(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioServiceIn.getUsuarioIn(id).get());
    }

    @GetMapping("admin/ObtenerUser/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioAdmin(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioServiceIn.getUsuarioIn(id).get());
    }

    @PutMapping("Users/responsable/ActualizarUser/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuarioRespnsable(@PathVariable Long id, @RequestBody RequestUsuario requestUsuario) {
        return ResponseEntity.ok(usuarioServiceIn.updateUsuarioIn(id, requestUsuario));
    }

    @PutMapping("Users/delegante/ActualizarUser/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuarioDelegante(@PathVariable Long id, @RequestBody RequestUsuario requestUsuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioServiceIn.updateUsuarioIn(id, requestUsuario));

    }

    @PutMapping("admin/ActualizarUser/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuarioAdmin(@PathVariable Long id, @RequestBody RequestUsuario requestUsuario) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioServiceIn.updateUsuarioIn(id, requestUsuario));

    }
    @DeleteMapping("admin/EliminarUsuario/{id}")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioServiceIn.deleteUsuarioIn(id));

    }

    @GetMapping("admin/ObtenerUsers")
    public ResponseEntity<List<UsuarioDTO>> listaUsuarios() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioServiceIn.obtenerTodasIn());
    }

}
