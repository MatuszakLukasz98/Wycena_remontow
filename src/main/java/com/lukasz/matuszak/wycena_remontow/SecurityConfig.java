package com.lukasz.matuszak.wycena_remontow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // wyłącz CSRF dla Postmana
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // wszystko dostępne bez logowania
                );
        return http.build();
    }
}