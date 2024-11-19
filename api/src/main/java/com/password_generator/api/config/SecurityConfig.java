package com.password_generator.api.config;

import com.password_generator.api.filters.JwtAuthenticationFilter;
import com.password_generator.api.filters.SqlInjectionFilter;
import com.password_generator.api.pojo.UserPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Collection;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:user.properties")
public class SecurityConfig {
    //@Qualifier("userPojo")
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
/*

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService service) {
         this.userDetailsService = service;
    }
*/



   @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity request) throws Exception {
        /*request.csrf(a -> a.disable());
        request.authorizeHttpRequests(rqst -> rqst.anyRequest().authenticated());
        request.formLogin(Customizer.withDefaults());
        request.httpBasic(Customizer.withDefaults());*/
        //request.csrf(customizer -> customizer.disable());
        //request.addFilter(new SqlInjectionFilter());
        return request
                .authorizeHttpRequests(rqst -> rqst.anyRequest()
                        .authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        //daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
