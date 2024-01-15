package com.momoc.multi.chat.room.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author momoc
 * @version 1.0
 * @className RedisUtils
 * @description
 * @date 2023/8/11 14:18
 */
@RequiredArgsConstructor
@Component
public class RedisUtils {

    final RedisTemplate<String,Object> redisTemplate;
    final RedisTemplate<String, String> stringRedisTemplate;


    /**
     * 获取数据通过key
     * @param key
     * @return
     * @param <T>
     * @throws Exception
     */
    public <T>  T getObjByKey(String key) throws Exception {
        Object value = redisTemplate.opsForValue().get(key);
        return (T) value;
    }

    public void setObject(String key, Object obj, int seconds) throws Exception {
        redisTemplate.opsForValue().set(key,obj, seconds, TimeUnit.SECONDS);
    }
    public void setObject(String key, Object obj) throws Exception {
        redisTemplate.opsForValue().set(key,obj);
    }

    public String getByKey(String key) throws Exception {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, String obj, int seconds) throws Exception {
        stringRedisTemplate.opsForValue().set(key,obj, seconds, TimeUnit.SECONDS);
    }
    public void set(String key, String obj) throws Exception {
        stringRedisTemplate.opsForValue().set(key,obj);
    }
    public Long increment(String key){
        return stringRedisTemplate.opsForValue().increment(key);
    }
}
