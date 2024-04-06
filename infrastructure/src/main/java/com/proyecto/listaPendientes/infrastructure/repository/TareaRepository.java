package com.proyecto.listaPendientes.infrastructure.repository;

import com.proyecto.listaPendientes.infrastructure.entity.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<TareaEntity,Long> {
}
