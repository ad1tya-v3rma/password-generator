package com.password_generator.api.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public interface JwtService {
    default String generateToken(String username, Key key)
    {
        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60*60*30)))
                .and().signWith(key).compact();
    }

    default Key generateSecretKey(String key)
    {
        byte[] bytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(bytes);
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
}
