package com.proyecto.listaPendientes.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolDTO {
    private String nombreRol;
}
