package cn.eliadoarias.springdemo.util;

import cn.eliadoarias.springdemo.config.JwtConfig;
import cn.eliadoarias.springdemo.constant.ExceptionEnum;
import cn.eliadoarias.springdemo.exception.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Resource
    private JwtConfig jwtConfig;

    public String generateToken(String username, Integer userId) {
        return Jwts.builder()
                .header().add("alg", "HS256").add("typ", "JWT").and()
                .subject(username)
                .issuer("Server")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+jwtConfig.getExpirationTime()))
                .audience().add("User").and()
                .claims().add("username",username).add("user_id",userId).and()
                .signWith(jwtConfig.getKey())
                .compact();
    }

    public Claims parseToken(String token){
        JwtParser jwtParser = Jwts.parser()
                .verifyWith(jwtConfig.getKey())
                .build();
        Jws<Claims> jws = jwtParser.parseSignedClaims(token);
        return jws.getPayload();
    }

    public static String extractToken(String baseToken){
        if(baseToken != null && baseToken.startsWith("Bearer ")){
            return baseToken.substring(7);
        } throw new ApiException(ExceptionEnum.WRONG_TOKEN);
    }

    public Integer getUserIdFromAuthorization(String baseToken){
        String token = extractToken(baseToken);
        return parseToken(token).get("user_id", Integer.class);
    }

    public String refreshToken(String baseToken){
        String token = extractToken(baseToken);
        Date now = new Date();
        Date expiration = parseToken(token).getExpiration();
        long deltaTime = expiration.getTime() - now.getTime();
        if(deltaTime <= jwtConfig.getRefreshTime()){
            String username = parseToken(token).get("username", String.class);
            Integer userId = parseToken(token).get("user_id", Integer.class);
            return this.generateToken(username, userId);
        }
        return token;
    }

    public boolean needRefresh(String baseToken){
        String token = extractToken(baseToken);
        return !parseToken(token).getExpiration().before(new Date(System.currentTimeMillis()+jwtConfig.getRefreshTime()));
    }

    public boolean canRefresh(HttpSession session){
        long refreshTime = session.getAttribute("refresh_time")==null?0:(Long)session.getAttribute("refresh_time");
        return refreshTime > System.currentTimeMillis();
    }

}
