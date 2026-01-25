package com.cpaums.config;

import com.cpaums.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/versions/latest").permitAll()
                .requestMatchers("/api/update/check").permitAll()
                
                .requestMatchers(HttpMethod.GET, "/api/versions/**").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/versions/**").hasAnyRole("DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/versions/**").hasAnyRole("DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/versions/**").hasAnyRole("DEVELOPER", "ADMIN")
                
                .requestMatchers(HttpMethod.POST, "/api/devices").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/devices").hasAnyRole("DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/devices/user/**").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/devices/*/version").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/devices/**").hasAnyRole("DEVELOPER", "ADMIN")
                
                .requestMatchers(HttpMethod.POST, "/api/update/log").hasAnyRole("USER", "DEVELOPER", "ADMIN")
                
                .requestMatchers("/api/stats/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}