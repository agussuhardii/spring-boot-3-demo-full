package com.agussuhardi.springdemofull.config.security;

import com.agussuhardi.springdemofull.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtService {


    @Value("${demo.secret.key}")
    private String secretKey;

    private Key hmacKey;

    @PostConstruct
    private void init() {
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey),
                SignatureAlgorithm.HS256.getJcaName());
    }


    public String create(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claim(User.Fields.roles, user.getAuthorities())
                .setSubject(user.getUsername())
                .setId(user.getId())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7, ChronoUnit.DAYS)))
                .signWith(hmacKey)
                .compact();


    }

    public Map<String, Object> getPayload(String jwt) {
        try {
            var split = jwt.split("\\.");
            var bytes = Base64.getDecoder().decode(split[1].getBytes(StandardCharsets.UTF_8));
            TypeReference<Map<String, Object>> tr = new TypeReference<>() {
            };
            return new ObjectMapper().readValue(bytes, tr);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    public Jws<Claims> parseJwt(String jwtString) {
        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);
    }


}
