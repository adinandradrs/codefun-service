package id.codefun.service.configuration;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.lettuce.core.RedisClient;

@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {
    
    private String host;
    private String password;
    private Integer port;
    private Integer dbIndex;

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

    @Bean(name="redisClient")
    public RedisClient getRedisClient(){
        StringBuilder propBuilder = new StringBuilder();
        propBuilder.append("redis://");
        propBuilder.append(this.getPassword());
        propBuilder.append("@");
        propBuilder.append(this.getHost());
        propBuilder.append(":");
        propBuilder.append(this.getPort());
        propBuilder.append("/");
        propBuilder.append(ObjectUtils.isEmpty(this.dbIndex) ? 0 : this.dbIndex);
        return RedisClient.create(propBuilder.toString());
    }

}
