package com.proyecto.listaPendientes.domain.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReniec {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroDocumento;
}
