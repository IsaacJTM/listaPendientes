package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;
import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;

import com.proyecto.listaPendientes.infrastructure.mapper.CategoriaMapper;
import com.proyecto.listaPendientes.infrastructure.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriaAdapterTest {

    @Mock
    private CategoriaMapper categoriaMapper;
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaAdapter categoriaAdapter;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testObtenerCategoriaAllOut() {
        // Datos de prueba
        CategoriaEntity categoria1 = new CategoriaEntity(/*Datos de prueba*/);
        CategoriaEntity categoria2 = new CategoriaEntity(/*Datos de prueba*/);
        List<CategoriaEntity> catedoriBD = new ArrayList<>();
        catedoriBD.add(categoria1);
        catedoriBD.add(categoria2);

        // Mock de usuarioRepository.findAll() para que devuelva los datos de prueba
        when(categoriaRepository.findAll()).thenReturn(catedoriBD);

        // Mock de usuarioMapper.mapToDTO() para devolver un UsuarioDTO esperado
        CategoriaDTO categoriaDTO1 = new CategoriaDTO();
        CategoriaDTO categoriaDTO2 = new CategoriaDTO();
        when(categoriaMapper.mapToDTO(categoria1)).thenReturn(categoriaDTO1);
        when(categoriaMapper.mapToDTO(categoria2)).thenReturn(categoriaDTO2);

        // Ejecutar el método a probar
        List<CategoriaDTO> resultado = categoriaAdapter.obtenerCategoriaAllOut();

        // Verificar el resultado
        assertEquals(2, resultado.size()); // Verificar que se devuelva el tamaño esperado de la lista
    }

    @Test
    void testUpdateCategoriaOut_CategoriaExistente() {
        // Datos de prueba
        Long id = 1L;
        RequestCategoria requestCategoria = new RequestCategoria(/* Datos de prueba */);
        CategoriaEntity categoriaEntity = new CategoriaEntity(/* Datos de prueba */);
        CategoriaDTO categoriaDTO = new CategoriaDTO(/* Datos de prueba */);

        // Configuración de los mocks
        when(categoriaRepository.existsById(id)).thenReturn(true);
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoriaEntity));
        when(categoriaMapper.mapToDTO(categoriaEntity)).thenReturn(categoriaDTO);

        // Ejecución del método a probar
        CategoriaDTO resultado = categoriaAdapter.actualzarCategoriaOut(id, requestCategoria);

        // Verificación de resultados
        assertEquals(categoriaDTO, resultado);
        verify(categoriaRepository).save(categoriaEntity); // Verificar que se llama a categoriaRepository.save()
    }

    @Test
    void testDeleteCategoriaOut_CategoriaNoExistente() {
        // Datos de prueba
        Long id = 1L;

        // Configurar el mock de categoriaRepository.existsById() para que devuelva false
        when(categoriaRepository.existsById(id)).thenReturn(false);

        // Configurar el mock de categoriaRepository.findById() para que devuelva un Optional vacío
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        // Ejecutar el método a probar
        CategoriaDTO resultado = categoriaAdapter.deleteCategoriaOut(id);

        // Verificar el resultado
        assertNull(resultado); // Verificar que el resultado sea null cuando no se encuentra la categoría

        // Verificar que no se llama a categoriaRepository.save() porque la categoría no se encontró
        verify(categoriaRepository, never()).save(any(CategoriaEntity.class));
    }
}