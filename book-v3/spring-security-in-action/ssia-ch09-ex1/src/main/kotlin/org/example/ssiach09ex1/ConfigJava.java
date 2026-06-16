package org.example.ssiach09ex1;

import org.example.ssiach09ex1.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ConfigJava {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAt(new CustomFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}
