package com.password_generator.api.controller;

import com.password_generator.api.service.PasswordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("generator")
@RequiredArgsConstructor
public class PasswordController {
    private final PasswordService passwordService;

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

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
