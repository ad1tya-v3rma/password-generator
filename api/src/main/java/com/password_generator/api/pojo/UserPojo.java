package com.password_generator.api.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserPojo implements UserDetailsService {
    private String username;
    private String password;
    private User userProps;

    public UserPojo(){}

    @Autowired
    UserPojo(User user)
    {
        this.userProps = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!this.userProps.getUsername().equals(username)){
            throw new UsernameNotFoundException("unknown user : "+username);
        }
        UserPojo user = new UserPojo();
        user.setUsername(username);
        user.setPassword(this.userProps.getPassword());
        return new UserPrincipal(user);
    }
}
