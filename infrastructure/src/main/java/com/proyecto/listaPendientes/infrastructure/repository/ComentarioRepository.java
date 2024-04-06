package com.proyecto.listaPendientes.infrastructure.repository;

import com.proyecto.listaPendientes.infrastructure.entity.ComentarioEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity,Long> {
    ComentarioEntity findByIdComentario(@Param("idComentario") String idComentario);
}
