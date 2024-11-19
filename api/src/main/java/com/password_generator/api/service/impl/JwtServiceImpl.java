package com.password_generator.api.service.impl;

import com.password_generator.api.service.JwtService;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@Getter
public class JwtServiceImpl implements JwtService {
    private final String key;

    JwtServiceImpl() {
        key = generateKey();
    }
}
