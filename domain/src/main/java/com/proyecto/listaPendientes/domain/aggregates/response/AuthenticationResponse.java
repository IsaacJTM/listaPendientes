package com.proyecto.listaPendientes.domain.aggregates.response;

import com.proyecto.listaPendientes.domain.aggregates.dto.RolDTO;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class AuthenticationResponse {
   private String token;
}
