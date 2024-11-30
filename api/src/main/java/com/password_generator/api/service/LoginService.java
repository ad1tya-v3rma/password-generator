package com.password_generator.api.service;

import com.password_generator.api.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    boolean checkLogin(UserDto userDto);
    boolean isValidUser(String presentedPass, String actualPass);
}
