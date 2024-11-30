package com.password_generator.api.pojo;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:user.properties")
@Configuration
@Getter
@Lazy(value = false)
public class UserUtils {
    @Value("${uname}")
    private String username;
    @Value("${password}")
    private String password;

    @PostConstruct
    public void init()
    {
        System.out.println("Username: " + username + " : Password: " + password);
    }
}
