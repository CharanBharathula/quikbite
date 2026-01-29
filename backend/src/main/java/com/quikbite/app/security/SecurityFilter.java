package com.quikbite.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.quikbite.app.exception.CustomAccessDeniedHandler;
import com.quikbite.app.exception.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration // Indicate that this class contains Spring configuration
@EnableWebSecurity // Enable Spring Security's web security support like a scenarion where it will allow or deny access to certain endpoints based on roles
@EnableMethodSecurity // Enable method-level security annotations such as @PreAuthorize and @PostAuthorize which means we can secure methods based on roles or permissions
@RequiredArgsConstructor
public class SecurityFilter {
    private final AuthFilter authFilter; // we need this filter to be registered in security configuration
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // to handle authentication errors
    private final CustomAccessDeniedHandler customAccessDeniedHandler; // to handle access denied errors

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable()) // Disable CSRF protection for stateless APIs (such as REST APIs)
        .cors(Customizer.withDefaults()) // Enable CORS with default settings which means allowing cross-origin requests such as requests from different domains
        .exceptionHandling( ex -> ex.accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint) ) // Configure custom handlers for authentication and access denied exceptions
        .authorizeHttpRequests( req -> req.requestMatchers("/api/auth/**", "/api/categories/**","/api/menu/**", "/api/reviews/**","/api/roles/**").permitAll().anyRequest().authenticated() ) // Define authorization rules for HTTP requests
        .sessionManagement(mag -> mag.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Set session management to stateless which means no session will be created or used by Spring Security
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    /**
     * Password encoder bean using BCrypt hashing algorithm.
     * Internally BCrypt is a strong hashing function that incorporates a salt to protect against rainbow table attacks
     * @return
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * This method provides the AuthenticationManager bean which is responsible for processing authentication requests
     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
