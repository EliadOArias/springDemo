package cn.eliadoarias.springdemo.config;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    @Getter
    private String baseKey;
    private SecretKey key;
    @Setter
    @Getter
    private long expirationTime;
    @Setter
    @Getter
    private long refreshTime;
    @Setter
    @Getter
    private long refreshLimit;

    public void setBaseKey(String baseKey) {
        key = Keys.hmacShaKeyFor(baseKey.getBytes(StandardCharsets.UTF_8));
        this.baseKey = baseKey;
    }
    public SecretKey getKey() {
        if(key == null){
            key = Keys.hmacShaKeyFor(baseKey.getBytes(StandardCharsets.UTF_8));
        }
        return key;
    }
}
