package com.password_generator.api.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier
public interface PasswordService  {
    Optional<String> getPassword();
}
