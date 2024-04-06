package com.proyecto.listaPendientes.infrastructure.patAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Getter
@Setter
@Component
public class PatAdapter {

   public static <T> String convertToJson(T dto){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dto);
        }catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }


    public static <T> T convertFromJson(String json, Class<T> valueType){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json,valueType);

        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
