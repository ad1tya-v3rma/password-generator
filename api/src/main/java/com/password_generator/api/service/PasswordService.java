package com.password_generator.api.service;

import java.util.Optional;

public interface PasswordService {
    Optional<String> getPassword();
}
