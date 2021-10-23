package id.codefun.service.configuration;

import java.time.Duration;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {
    
    private String host;
    private String password;
    private Integer port;
    private Integer dbIndex;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxTotal;
    private Long timeout;

    public void setHost(String host){
        this.host = host;
    }

    public String getHost(){
        return this.host;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPort(Integer port){
        this.port = port;
    }

    public Integer getPort(){
        return this.port;
    }

    public void setDbIndex(Integer dbIndex){
        this.dbIndex = dbIndex;
    }

    public Integer getDbIndex(){
        return this.dbIndex;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    @Bean(name="redisClientFactory")
    public LettuceConnectionFactory getRedisClientFactory(){
        log.info("redisClientFactory is created...");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(this.getHost());
        redisStandaloneConfiguration.setPort(this.getPort());
        redisStandaloneConfiguration.setPassword(this.getPassword());
        redisStandaloneConfiguration.setDatabase(ObjectUtils.isEmpty(this.dbIndex) ? 0 : this.dbIndex);
        var poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(ObjectUtils.isEmpty(this.maxIdle) ? 10 : this.maxIdle);
        poolConfig.setMinIdle(ObjectUtils.isEmpty(this.minIdle) ? 1 : this.minIdle);
        poolConfig.setMaxTotal(ObjectUtils.isEmpty(this.maxTotal) ? 10 : this.maxTotal);
        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
            .commandTimeout(ObjectUtils.isEmpty(timeout) ? Duration.ofSeconds(10) : Duration.ofSeconds(timeout))
            .shutdownTimeout(Duration.ZERO)
            .poolConfig(poolConfig)
            .build();

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
        lettuceConnectionFactory.setShareNativeConnection(false);
        return lettuceConnectionFactory;
    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        log.info("redisTemplate is created...");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.getRedisClientFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setEnableDefaultSerializer(true);
        return redisTemplate;
    }

}