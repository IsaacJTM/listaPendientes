package com.proyecto.listaPendientes.infrastructure.repository;

import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    UsuarioEntity findByIdUsuario(@Param("idUsuario") String idUsuario);

}
