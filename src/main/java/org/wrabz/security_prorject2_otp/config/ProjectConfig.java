package org.wrabz.security_prorject2_otp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(c ->
                        c.disable())
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().permitAll());

        return http.build();
    }

    public PasswordEncoder passwordEncoder() {

        return new  BCryptPasswordEncoder();
    }
}
