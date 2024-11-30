package com.password_generator.api.controller;

import com.password_generator.api.dtos.ResponseDto;
import com.password_generator.api.dtos.UserDto;
import com.password_generator.api.service.JwtService;
import com.password_generator.api.service.LoginService;
import com.password_generator.api.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generator")
@RequiredArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final LoginService loginService;
    private final Logger LOG = LoggerFactory.getLogger(PasswordController.class);

    @GetMapping(value = "pwd")
    public String getSecurePassword() {
        return (passwordService.getPassword().isPresent() ? passwordService.getPassword().get() : "");
    }

    @PostMapping("/test")
    public String receiver(@RequestBody String var) {
        return var;
    }

    @GetMapping("/encode")
    public String encoder(@RequestBody String plain) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(plain);
    }

    @GetMapping("/jwt")
    public String getJwtToken(@RequestBody String username) {
        String key = jwtService.getKey();
        LOG.info("generate plaintext key : " + key);
        return jwtService.generateToken(username);
    }

    @GetMapping("/csrf")
    @CrossOrigin
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseDto authenticateUser(@RequestBody UserDto user) {
        boolean result = loginService.checkLogin(user);
        ResponseDto response = new ResponseDto();
        response.setResult(result);
        return response;
    }
}
