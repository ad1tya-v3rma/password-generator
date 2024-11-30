package com.password_generator.api.service.impl;

import com.password_generator.api.dtos.UserDto;
import com.password_generator.api.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {

    private final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
    private UserDetailsService userDetailsService;

    @Autowired
    LoginServiceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean checkLogin(UserDto userDto) {
        UserDetails loadUser;
        try {
            loadUser = userDetailsService.loadUserByUsername(userDto.getUsername());
        } catch (Exception e) {
            LOG.error("unable to login", e);
            return false;
        }
        if (loadUser != null &&
                (loadUser.isEnabled()
                        && loadUser.isAccountNonExpired()
                        && loadUser.isAccountNonLocked()
                        && loadUser.isCredentialsNonExpired()
                )) {
            return isValidUser(userDto.getPassword(), loadUser.getPassword());
        }
        return false;
    }

    @Override
    public boolean isValidUser(String presentedPass, String actualPass) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return bCryptPasswordEncoder.matches(presentedPass, actualPass);
    }
}
