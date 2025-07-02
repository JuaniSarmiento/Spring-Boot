package com.informaticonfig.spring.app1.proyecto4.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // No te olvides el @Bean que ya tenías para el PasswordEncoder
    // @Bean
    // public PasswordEncoder passwordEncoder() { ... }

    // ... en SecurityConfig.java

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // ¡ACÁ AGREGAMOS LA RUTA NUEVA!
                        .requestMatchers(HttpMethod.GET, "/api/eventos", "/api/usuarios", "/api/reservas/usuario/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/eventos", "/api/usuarios", "/api/reservas").permitAll()

                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}