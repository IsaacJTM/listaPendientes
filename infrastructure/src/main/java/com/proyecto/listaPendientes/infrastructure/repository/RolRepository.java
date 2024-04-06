package com.proyecto.listaPendientes.infrastructure.repository;

import com.proyecto.listaPendientes.infrastructure.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<RolEntity,Long> {

   RolEntity findByNombreRol(String nombreRol);
}
