package org.example.ssiach07ex3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();
        var user1 = User.builder()
                .username("john")
                .password("12345")
                .authorities("ROLE_ADMIN")
                .build();

        var user2 = User.builder()
                .username("jane")
                .password("12345")
                .authorities("ROLE_MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET, "/hello").hasAnyRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/ciao").hasAnyRole("MANAGER")
                    .requestMatchers(HttpMethod.GET, "/a").authenticated()
                    .requestMatchers(HttpMethod.POST, "/a").permitAll()
                    .requestMatchers(HttpMethod.GET, "/product/*").authenticated()
                    .anyRequest().hasAnyRole("ADMIN");
        });


        return http.build();
    }
}
