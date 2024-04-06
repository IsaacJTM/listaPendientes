package com.proyecto.listaPendientes.infrastructure.adapter;

import com.proyecto.listaPendientes.domain.aggregates.constants.Constants;
import com.proyecto.listaPendientes.domain.aggregates.dto.UsuarioDTO;
import com.proyecto.listaPendientes.domain.aggregates.request.RequestUsuario;
import com.proyecto.listaPendientes.domain.port.out.UsuarioServiceOut;
import com.proyecto.listaPendientes.infrastructure.entity.UsuarioEntity;
import com.proyecto.listaPendientes.infrastructure.mapper.UsuarioMapper;
import com.proyecto.listaPendientes.infrastructure.patAdapter.PatAdapter;
import com.proyecto.listaPendientes.infrastructure.redis.RedisService;
import com.proyecto.listaPendientes.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioAdapter implements UsuarioServiceOut {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final RedisService redisService;


    @Override
    public Optional<UsuarioDTO> getUsuarioOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_USUARIO + id);
        if(redisInfo != null){
            UsuarioDTO usuarioDTO = PatAdapter.convertFromJson(redisInfo,UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        }else{
        UsuarioDTO dto = usuarioMapper.mapToDTO(usuarioRepository.findById(id).get());
        String redis = PatAdapter.convertToJson(dto);
        redisService.saveRedis(Constants.REDIS_KEY_USUARIO+id,redis,1);
        return Optional.ofNullable(dto);
        }
    }

    @Override
    public UsuarioDTO updateUsuarioOut(Long id, RequestUsuario requestUsuario) {
        boolean existe = usuarioRepository.existsById(id);
        if(existe){
            Optional<UsuarioEntity> entity = usuarioRepository.findById(id);
            usuarioRepository.save(getEntityUpdate(entity.get(), requestUsuario));
            return usuarioMapper.mapToDTO(getEntityUpdate(entity.get(), requestUsuario));
        }
        return null;
    }

    @Override
    public UsuarioDTO deleteUsuarioOut(Long id) {
        boolean existe = usuarioRepository.existsById(id);
        if(existe){
            Optional<UsuarioEntity> entity = usuarioRepository.findById(id);
            entity.get().setEstadoUsuario(0);
            entity.get().setUserDelete(Constants.AUDIT_ADMIN);
            entity.get().setUserDateDelet(getTimestamp());
            usuarioRepository.save(entity.get());
            return usuarioMapper.mapToDTO(entity.get());
        }
        return null;
    }

    @Override
    public List<UsuarioDTO> obtenerTodasOut() {
        List<UsuarioDTO> listUsers = new ArrayList<>();
        List<UsuarioEntity> usuariosBD = usuarioRepository.findAll();
        for(UsuarioEntity  usuarios : usuariosBD){
            UsuarioDTO usuarioDTO = usuarioMapper.mapToDTO(usuarios);
            listUsers.add(usuarioDTO);
        }
        return listUsers;
    }

    private UsuarioEntity getEntityUpdate(UsuarioEntity usuarioActualizar, RequestUsuario requestUsuario){
        usuarioActualizar.setNombreUsuario(requestUsuario.getNombreUsuario());
        usuarioActualizar.setApellidosUsuario(requestUsuario.getApellidosUsuario());
        usuarioActualizar.setTelefono(requestUsuario.getTelefono());
        usuarioActualizar.setEdad(requestUsuario.getEdad());
        if (requestUsuario.getPassword() != null) {
            usuarioActualizar.setPassword(new BCryptPasswordEncoder().encode(requestUsuario.getPassword()));
        }
        usuarioActualizar.setUserUpdate(Constants.AUDIT_ADMIN);
        usuarioActualizar.setUserDateUpdate(getTimestamp());
        return usuarioActualizar;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByEmail(username).orElseThrow(()->
                        new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }
}
