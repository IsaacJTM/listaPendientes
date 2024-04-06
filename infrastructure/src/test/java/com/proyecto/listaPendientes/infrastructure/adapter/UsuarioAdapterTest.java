package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestUsuario;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.UsuarioMapper;
import com.proyecto.listaPendientes.infrastructure.redis.RedisService;
import com.proyecto.listaPendientes.infrastructure.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioAdapterTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private RedisService redisService;

    @InjectMocks
    private UsuarioAdapter usuarioAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testObtenerTodasOut() {
        // Datos de prueba
        UsuarioEntity usuario1 = new UsuarioEntity(/*Datos de prueba*/);
        UsuarioEntity usuario2 = new UsuarioEntity(/*Datos de prueba*/);
        List<UsuarioEntity> usuariosBD = new ArrayList<>();
        usuariosBD.add(usuario1);
        usuariosBD.add(usuario2);

        // Mock de usuarioRepository.findAll() para que devuelva los datos de prueba
        when(usuarioRepository.findAll()).thenReturn(usuariosBD);

        // Mock de usuarioMapper.mapToDTO() para devolver un UsuarioDTO esperado
        UsuarioDTO usuarioDTO1 = new UsuarioDTO();
        UsuarioDTO usuarioDTO2 = new UsuarioDTO();
        when(usuarioMapper.mapToDTO(usuario1)).thenReturn(usuarioDTO1);
        when(usuarioMapper.mapToDTO(usuario2)).thenReturn(usuarioDTO2);

        // Ejecutar el método a probar
        List<UsuarioDTO> resultado = usuarioAdapter.obtenerTodasOut();

        // Verificar el resultado
        assertEquals(2, resultado.size()); // Verificar que se devuelva el tamaño esperado de la lista
    }

    @Test
    void testUpdateUsuarioOut_UsuarioExistente() {
        // Datos de prueba
        Long id = 1L;
        RequestUsuario requestUsuario = new RequestUsuario();
        requestUsuario.setNombreUsuario("Nombre");
        requestUsuario.setApellidosUsuario("Apellidos");
        requestUsuario.setTelefono("123456789");
        requestUsuario.setEdad(30);
        requestUsuario.setPassword("contraseña");

        UsuarioEntity usuarioEntity = new UsuarioEntity(/* Datos de prueba */);
        UsuarioDTO usuarioDTO = new UsuarioDTO(/* Datos de prueba */);

        // Configuración de los mocks
        when(usuarioRepository.existsById(id)).thenReturn(true);
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioMapper.mapToDTO(usuarioEntity)).thenReturn(usuarioDTO);

        // Ejecución del método a probar
        UsuarioDTO resultado = usuarioAdapter.updateUsuarioOut(id, requestUsuario);

        // Verificación de resultados
        assertEquals(usuarioDTO, resultado);
        verify(usuarioRepository).save(usuarioEntity); // Verificar que se llama a usuarioRepository.save()
    }



    @Test
    void testDeleteUsuarioOut_UsuarioNoExistente() {
        // Datos de prueba
        Long id = 1L;

        // Configurar el mock de usuarioRepository.existsById() para que devuelva false
        when(usuarioRepository.existsById(id)).thenReturn(false);

        // Configurar el mock de usuarioRepository.findById() para que devuelva un Optional vacío
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecutar el método a probar
        UsuarioDTO resultado = usuarioAdapter.deleteUsuarioOut(id);

        // Verificar el resultado
        assertNull(resultado); // Verificar que el resultado sea null cuando no se encuentra el usuario

        // Verificar que no se llama a usuarioRepository.save() porque el usuario no se encontró
        verify(usuarioRepository, never()).save(any(UsuarioEntity.class));
    }

}