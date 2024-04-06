package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.constants.Constants;
import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestCategoria;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestTarea;
import com.proyecto.listaPendientes.domain.port.out.TareaServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;
import com.proyecto.listaPendientes.infrastructure.entity.ComentarioEntity;
import com.proyecto.listaPendientes.infrastructure.entity.TareaEntity;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.CategoriaMapper;
import com.proyecto.listaPendientes.infrastructure.mapper.TareaMapper;
import com.proyecto.listaPendientes.infrastructure.patAdapter.PatAdapter;
import com.proyecto.listaPendientes.infrastructure.redis.RedisService;
import com.proyecto.listaPendientes.infrastructure.repository.CategoriaRepository;
import com.proyecto.listaPendientes.infrastructure.repository.ComentarioRepository;
import com.proyecto.listaPendientes.infrastructure.repository.TareaRepository;
import com.proyecto.listaPendientes.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TareaAdapter implements TareaServiceOut {
    private final TareaMapper tareaMapper;
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioRepository comentarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final RedisService redisService;
    @Override
    public TareaDTO crearTareaOut(RequestTarea requestTarea) {
        tareaRepository.save(getEntity(requestTarea));
        return tareaMapper.mapToDTO(getEntity(requestTarea));
    }

    @Override
    public Optional<TareaDTO> obtenerTareaOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_USUARIO + id);
        if(redisInfo != null){
            TareaDTO tareaDTO = PatAdapter.convertFromJson(redisInfo,TareaDTO.class);
            return Optional.ofNullable(tareaDTO);
        }else{
            TareaDTO workDto = tareaMapper.mapToDTO(tareaRepository.findById(id).get());
            String redis = PatAdapter.convertToJson(workDto);
            redisService.saveRedis(Constants.REDIS_KEY_USUARIO+id,redis,1);
            return Optional.ofNullable(workDto);
        }

    }

    @Override
    public List<TareaDTO> obtenerTodasOut() {
        List<TareaDTO> tareaDTOList = new ArrayList<>();
        List<TareaEntity> entities = tareaRepository.findAll();
        for(TareaEntity tarea : entities){
            TareaDTO tareaDTO = tareaMapper.mapToDTO(tarea);
            tareaDTOList.add(tareaDTO);
        }
        return tareaDTOList;
    }

    @Override
    public TareaDTO actualzarTareaOut(Long id, RequestTarea requestTarea) {
        boolean existe = tareaRepository.existsById(id);
        if(existe){
            Optional<TareaEntity> entity = tareaRepository.findById(id);
            tareaRepository.save(getEntityUpdate(entity.get()));
            return tareaMapper.mapToDTO(getEntityUpdate(entity.get()));
        }
        return null;
    }

    @Override
    public TareaDTO deleteTareaOut(Long id) {
        boolean existe = tareaRepository.existsById(id);
        if(existe){
            Optional<TareaEntity> entity = tareaRepository.findById(id);
            entity.get().setUserDelete(Constants.AUDIT_ADMIN);
            entity.get().setUserDateDelet(getTimestamp());
            tareaRepository.save(entity.get());
            return tareaMapper.mapToDTO(entity.get());
        }
        return null;
    }


    private TareaEntity getEntity(RequestTarea requestTarea){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(requestTarea.getUsuario());
        CategoriaEntity categoriaEntity = categoriaRepository.findByIdCategoria(requestTarea.getUsuario());
        ComentarioEntity comentarioEntity = comentarioRepository.findByIdComentario(requestTarea.getUsuario());

        TareaEntity entity = new TareaEntity();
        entity.setTitulo(requestTarea.getTitulo());
        entity.setDescripcion(requestTarea.getDescripcion());
        entity.setFechaCreacion(getTimestamp());
        entity.setFechaVencimiento((Timestamp)requestTarea.getFechaVencimiento());
        entity.setUserCreate(Constants.AUDIT_ADMIN);
        entity.setUserDateCreate(getTimestamp());
        entity.setUsuario(usuarioEntity);
        entity.setCategoria(categoriaEntity);
        entity.setComentario(comentarioEntity);
        return entity;
    }

    private TareaEntity getEntityUpdate(TareaEntity tareaActualizar){
        tareaActualizar.setTitulo(tareaActualizar.getTitulo());
        tareaActualizar.setDescripcion(tareaActualizar.getDescripcion());
        tareaActualizar.setUserUpdate(Constants.AUDIT_ADMIN);
        tareaActualizar.setFechaVencimiento(tareaActualizar.getFechaVencimiento());
        return tareaActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
