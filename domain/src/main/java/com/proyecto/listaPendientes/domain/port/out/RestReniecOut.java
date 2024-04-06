package com.proyecto.listaPendientes.domain.port.out;


import com.proyecto.listaPendientes.domain.aggregates.response.ResponseReniec;

public interface RestReniecOut {
    ResponseReniec getInfoReniec(String numDoc);
}
