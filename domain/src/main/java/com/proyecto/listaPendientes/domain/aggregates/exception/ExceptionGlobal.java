package com.proyecto.listaPendientes.domain.aggregates.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.listaPendientes.domain.aggregates.response.ResponseBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ExceptionGlobal{

    private final ObjectMapper objectMapper;

    public ExceptionGlobal(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejoException(Exception exception) throws JsonProcessingException {

        ResponseBase responseBase = new ResponseBase();
        responseBase.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBase.setMensaje("Error: " + exception.getMessage());
        responseBase.setExito(false);
        responseBase.setData(Optional.empty());

       String jsonResponse = objectMapper.writeValueAsString(responseBase);


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);

    }
}
