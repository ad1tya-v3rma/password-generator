package com.password_generator.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {
    final Logger LOG = LoggerFactory.getLogger(JwtService.class);

    default String generateToken(String username)
    {
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60*60*30)))
                .and().signWith(generateSecretKey(getKey())).compact();
    }

    default SecretKey generateSecretKey(String key)
    {
        byte[] bytes = Decoders.BASE64.decode(key);
        SecretKey sk = Keys.hmacShaKeyFor(bytes);
        return sk;
    }

    default String generateKey()
    {
        try {
            SecretKey sk = KeyGenerator.getInstance("HmacSHA256").generateKey();
            return Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    default String extractUserName(String token){

        return extractClaim(token, Claims::getSubject);
    }

    default <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    default Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(generateSecretKey(getKey())).build().parseSignedClaims(token).getPayload();
    }

    default boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        boolean result1 = username.equals(userDetails.getUsername());
        boolean result2 = !isExpired(token);
        return result2 && result1;
    }

    default boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    default Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    String getKey();


}
