package com.password_generator.api.service.impl;

import com.password_generator.api.pojo.UserPrincipal;
import com.password_generator.api.pojo.UserUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserDetailsServiceImpl implements UserDetailsService {
    private String username;
    private String password;
    private UserUtils userUtilsProps;

    public UserDetailsServiceImpl(){}

    @Autowired
    UserDetailsServiceImpl(UserUtils userUtils)
    {
        this.userUtilsProps = userUtils;
        this.username = userUtils.getUsername();
        this.password = userUtils.getPassword();
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!this.userUtilsProps.getUsername().equals(username)){
            throw new UsernameNotFoundException("unknown user : "+username);
        }
        UserDetailsServiceImpl user = new UserDetailsServiceImpl();
        user.setUsername(username);
        user.setPassword(this.userUtilsProps.getPassword());
        return new UserPrincipal(user);
    }
}
