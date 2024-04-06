package com.proyecto.listaPendientes.infrastructure.repository;

import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Long> {
    CategoriaEntity findByIdCategoria(@Param("idCategoria") String idCategoria);
}
