package id.codefun.service.util;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Component
public class CacheUtility {

    private RedisClient redisClient;

    public CacheUtility(RedisClient redisClient){
        this.redisClient = redisClient;
    }

    public void delete(String prefix, String key){
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommand = connection.sync();
        String pair = prefix + ":" + key;
        syncCommand.del(pair);
        connection.close();
    }

    public void delete(String prefix){
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommand = connection.sync();
        String pair = prefix + "*";
        List<String> keys = syncCommand.keys(pair);
        for (String key: keys) {
            syncCommand.del(key);
        }
        connection.close();
    }

    public void set(String prefix, String key, String value, Integer expiration){
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommand = connection.sync();
        String pair = prefix + ":" + key;
        String existingCache = syncCommand.get(pair);
        if(StringUtils.isNotEmpty(existingCache)){
            syncCommand.del(pair);
        }
        syncCommand.set(pair, value);
        if(ObjectUtils.isNotEmpty(expiration)){
            syncCommand.expire(pair, expiration);
        }
        connection.close();
    }

    public String get(String prefix, String key){
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommand = connection.sync();
        String pair = prefix + ":" + key;
        String value = syncCommand.get(pair);
        if(StringUtils.isEmpty(value)){
            return null;
        }
        connection.close();
        return value;
    }
    
}
