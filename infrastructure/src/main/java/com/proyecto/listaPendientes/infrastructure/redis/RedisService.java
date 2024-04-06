package com.proyecto.listaPendientes.infrastructure.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;
    //Guardar los valores en redis
    public void saveRedis(String key, String value, int exp){
        stringRedisTemplate.opsForValue().set(key, value);
        //tiempo de expiración para la clave
        stringRedisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }

    public String getFromRedis(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key){
        stringRedisTemplate.delete(key);
    }
}
