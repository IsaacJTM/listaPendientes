package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.constants.Constants;
import com.proyecto.listaPendientes.domain.aggregates.dto.CategoriaDTO;
import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;
import com.proyecto.listaPendientes.domain.port.out.CategoriaServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.CategoriaMapper;
import com.proyecto.listaPendientes.infrastructure.patAdapter.PatAdapter;
import com.proyecto.listaPendientes.infrastructure.redis.RedisService;
import com.proyecto.listaPendientes.infrastructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaAdapter implements CategoriaServiceOut{

    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepository categoriaRepository;
    private final RedisService redisService;
    @Override
    public CategoriaDTO crearCategoriaOut(RequestCategoria requestCategoria) {
        categoriaRepository.save(getEntity(requestCategoria));
        return categoriaMapper.mapToDTO(getEntity(requestCategoria));
    }

    @Override
    public Optional<CategoriaDTO> obtenerCategoriaOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_USUARIO + id);
        CategoriaDTO categoriaDTO;
        if(redisInfo != null){
            categoriaDTO = PatAdapter.convertFromJson(redisInfo, CategoriaDTO.class);
            return Optional.ofNullable(categoriaDTO);
        }else{
            categoriaDTO = categoriaMapper.mapToDTO(categoriaRepository.findById(id).get());
            String redis = PatAdapter.convertToJson(categoriaDTO);
            redisService.saveRedis(Constants.REDIS_KEY_USUARIO+id,redis,1);
            return Optional.ofNullable(categoriaDTO);
        }

    }

    @Override
    public List<CategoriaDTO> obtenerCategoriaAllOut() {
        List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
        List<CategoriaEntity> entities = categoriaRepository.findAll();
        for(CategoriaEntity categoria : entities){
            CategoriaDTO categoriaDTO = categoriaMapper.mapToDTO(categoria);
            categoriaDTOList.add(categoriaDTO);
        }
        return categoriaDTOList;
    }

    @Override
    public CategoriaDTO actualzarCategoriaOut(Long id, RequestCategoria requestCategoria) {
        boolean existe = categoriaRepository.existsById(id);
        if(existe){
            Optional<CategoriaEntity> entity = categoriaRepository.findById(id);
            categoriaRepository.save(getEntityUpdate(entity.get(),requestCategoria));
            return categoriaMapper.mapToDTO(getEntityUpdate(entity.get(),requestCategoria));
        }
        return null;
    }

    @Override
    public CategoriaDTO deleteCategoriaOut(Long id) {
        boolean existe = categoriaRepository.existsById(id);
        if(existe){
            Optional<CategoriaEntity> entity = categoriaRepository.findById(id);
            entity.get().setEstadoParentesco(0);
            entity.get().setUserDelete(Constants.AUDIT_ADMIN);
            entity.get().setUserDateDelet(getTimestamp());
            categoriaRepository.save(entity.get());
            return categoriaMapper.mapToDTO(entity.get());
        }
        return null;
    }


    private CategoriaEntity getEntity(RequestCategoria requestCategoria){
        CategoriaEntity entity = new CategoriaEntity();
        entity.setNombreCategoria(requestCategoria.getNombreCategoria());
        entity.setDescripcionCategoria(requestCategoria.getDescripcion());
        entity.setEstadoParentesco(Constants.STATUS_ACTIVE);
        entity.setUserCreate(Constants.AUDIT_ADMIN);
        entity.setUserDateCreate(getTimestamp());
        return entity;
    }

    private CategoriaEntity getEntityUpdate(CategoriaEntity categoriaActualizar, RequestCategoria requestCategoria){
        categoriaActualizar.setNombreCategoria(requestCategoria.getNombreCategoria());
        categoriaActualizar.setDescripcionCategoria(requestCategoria.getDescripcion());
        categoriaActualizar.setUserUpdate(Constants.AUDIT_ADMIN);
        categoriaActualizar.setUserDateUpdate(getTimestamp());
        return categoriaActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
