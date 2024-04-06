package com.proyecto.listaPendientes.infrastructure.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.proyecto.listaPendientes.domain.aggregates.constants.Constants;
import com.proyecto.listaPendientes.domain.aggregates.dto.TareaDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestTarea;
import com.proyecto.listaPendientes.domain.port.out.TareaServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.CategoriaEntity;
import com.proyecto.listaPendientes.infrastructure.entity.ComentarioEntity;
import com.proyecto.listaPendientes.infrastructure.entity.TareaEntity;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public TareaDTO crearTareaOut(RequestTarea requestTarea) throws JsonProcessingException {
      try {
          TareaEntity tareaGuardar = getEntity(requestTarea);
          tareaRepository.save(tareaGuardar);
          return tareaMapper.mapToDTO(tareaGuardar);
      }catch (Exception ex){
            TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setMensaje("Error" + ex.getMessage());
            return tareaDTO;
      }
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
            tareaRepository.save(getEntityUpdate(entity.get(),requestTarea));
            return tareaMapper.mapToDTO(getEntityUpdate(entity.get(),requestTarea));
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
            entity.get().setEstadoTarea(0);
            tareaRepository.save(entity.get());
            return tareaMapper.mapToDTO(entity.get());
        }
        return null;
    }


    private TareaEntity getEntity(RequestTarea requestTarea) {
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(requestTarea.getUsuario());
        if (usuarioEntity == null) {
            return null;
        }
        CategoriaEntity categoriaEntity = categoriaRepository.findByIdCategoria(requestTarea.getCategoria());
        ComentarioEntity comentarioEntity = comentarioRepository.findByIdComentario(String.valueOf(requestTarea.getComentario()));


        TareaEntity entity = new TareaEntity();
        entity.setTitulo(requestTarea.getTitulo());
        entity.setDescripcion(requestTarea.getDescripcion());
        entity.setFechaCreacion(getTimestamp());
        entity.setFechaVencimiento(convertirAFechaTimestamp(requestTarea.getFechaVencimiento()));
        entity.setEstadoTarea(Constants.STATUS_ACTIVE);
        entity.setUserDateCreate(getTimestamp());
        entity.setUsuario(usuarioEntity);
        entity.setComentario(comentarioEntity);
        entity.setCategoria(categoriaEntity);

        return entity;
    }

    private TareaEntity getEntityUpdate(TareaEntity tareaActualizar, RequestTarea requestTarea){
        UsuarioEntity usuarioEntity = usuarioRepository.findByIdUsuario(requestTarea.getUsuario());
        CategoriaEntity categoriaEntity = categoriaRepository.findByIdCategoria(requestTarea.getCategoria());
        ComentarioEntity comentarioEntity = comentarioRepository.findByIdComentario(requestTarea.getComentario());

        tareaActualizar.setTitulo(requestTarea.getTitulo());
        tareaActualizar.setDescripcion(requestTarea.getDescripcion());
        tareaActualizar.setFechaVencimiento(convertirAFechaTimestamp(requestTarea.getFechaVencimiento()));
        tareaActualizar.setUserDateUpdate(getTimestamp());
        tareaActualizar.setFechaVencimiento(tareaActualizar.getFechaVencimiento());
        tareaActualizar.setUsuario(usuarioEntity);
        tareaActualizar.setCategoria(categoriaEntity);
        tareaActualizar.setComentario(comentarioEntity);
        return tareaActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private static Timestamp convertirAFechaTimestamp(String fechaString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaDate = dateFormat.parse(fechaString);
            return new Timestamp(fechaDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
