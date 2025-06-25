package com.informaticonfig.spring.app1.proyecto2springboot.Seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SegurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // PERMISOS PARA PELÍCULAS
                        .requestMatchers(HttpMethod.GET, "/peliculas", "/peliculas/**").permitAll() // Cualquiera puede VER películas
                        .requestMatchers(HttpMethod.POST, "/peliculas").hasRole("ADMIN") // Solo ADMINS pueden CREAR películas

                        // PERMISOS PARA RESEÑAS
                        .requestMatchers(HttpMethod.GET, "/peliculas/{idPelicula}/reseñas").permitAll() // Cualquiera puede VER reseñas
                        .requestMatchers(HttpMethod.POST, "/peliculas/{idPelicula}/reseñas").hasRole("USER") // Solo USERS pueden CREAR reseñas

                        // Para cualquier otra petición, se requiere estar autenticado
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Usamos Autenticación Básica para que funcione con Insomnia

        return http.build();
    }

}
