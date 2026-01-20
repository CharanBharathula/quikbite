package com.quikbite.app.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JWTUtils {

    private static final long EXPIRATION_TIME = 30l * 24 * 60 * 60 * 1000;
    private SecretKey key;
    
    @Value("${secretKey}")
    private String secretKeyJwtString;

    @PostConstruct
    private void init(){
        byte[] keyByte = secretKeyJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
        log.info("JWT Secret Key initialized successfully.");
    }

    public String generateToken(String userName){
        return Jwts.builder()
                .subject(userName)
                .signWith(key)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * It extracts the claims from the JWT token using the provided function.
     * its generic because the return type can vary based on the function provided such as getting subject, expiration date etc.
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = getUserNameFromJwtToken(token);
        return ( userName.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
