package com.password_generator.api.controller;

import com.password_generator.api.service.JwtService;
import com.password_generator.api.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

@RestController
@RequestMapping("generator")
@RequiredArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;
    private final JwtService jwtService;
    @GetMapping(value = "pwd")
    public String getSecurePassword()
    {
        return (passwordService.getPassword().isPresent() ? passwordService.getPassword().get() : "");
    }

    @PostMapping("/test")
    public String receiver(@RequestBody String var)
    {
        return var;
    }

    @GetMapping("/encode")
    public String encoder(@RequestBody String plain)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(plain);
    }

    @GetMapping("/jwt")
    public String getJwtToken(@RequestBody String username)
    {
        String key = jwtService.generateKey();
        Key secret = jwtService.generateSecretKey(key);
        String token = jwtService.generateToken(username, secret);
        return jwtService.generateToken(username, jwtService.generateSecretKey(jwtService.generateKey()));
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
