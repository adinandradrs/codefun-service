package id.codefun.service.util;

import java.time.Duration;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CacheUtility {

    private RedisTemplate<String, Object> redisTemplate;

    public CacheUtility(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void delete(String prefix, String key){
        String pair = prefix + ":" + key;
        redisTemplate.delete(pair);
    }

    public void delete(String prefix){
        String pair = prefix + "*";
        redisTemplate.keys(pair).forEach(key->{
            redisTemplate.delete(key);
        });
    }

    public void set(String prefix, String key, String value, Integer expiration){
        String pair = prefix + ":" + key;
        if(ObjectUtils.isNotEmpty(expiration)){
            redisTemplate.opsForValue().set(pair, value, Duration.ofSeconds(expiration));
        }
        else{
            redisTemplate.opsForValue().set(pair, value);
        }
    }

    public String get(String prefix, String key){
        String pair = prefix + ":" + key;
        Object value = redisTemplate.opsForValue().get(pair);
        if(ObjectUtils.isEmpty(value)){
            return null;
        } 
        return value.toString();
    }
    
}
