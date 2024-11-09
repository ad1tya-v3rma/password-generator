package com.password_generator.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity request) throws Exception {
        /*request.csrf(a -> a.disable());
        request.authorizeHttpRequests(rqst -> rqst.anyRequest().authenticated());
        request.formLogin(Customizer.withDefaults());
        request.httpBasic(Customizer.withDefaults());*/
        //request.csrf(customizer -> customizer.disable());
        return request
                .authorizeHttpRequests(rqst -> rqst.anyRequest()
                        .authenticated()).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public UserDetailsService getUserDetails()
    {
        /*return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        }*/
        UserDetails user1 = User.withDefaultPasswordEncoder().username("kelly").password("a").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1);
    }

}
