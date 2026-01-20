package com.quikbite.app.security;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quikbite.app.exception.CustomAuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component // Indicate that this class is a Spring component which can be auto-detected through classpath scanning
@RequiredArgsConstructor
@Slf4j

/**
 * This filter intercepts incoming HTTP requests to validate JWT tokens for authentication.
 * It extends OncePerRequestFilter to ensure that it is executed once per request.
 */
public class AuthFilter extends OncePerRequestFilter {
    private final JWTUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * This method is called for each HTTP request to perform filtering.
     * It extracts the JWT token from the request, validates it, and sets the authentication context
     * Internally FilterChain means the chain of filters that the request has to go through
     * flow of request -> filters -> controller -> response
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if( token != null ){
            String email;
            try{
                email = jwtUtils.getUserNameFromJwtToken(token);
            }catch (Exception e){
                AuthenticationException authException = new BadCredentialsException(e.getMessage());
                customAuthenticationEntryPoint.commence(request, response, authException);
                return;
            }

            /*
                *Here we are loading user details from DB using email extracted from JWT token
                *then validating the token and if valid setting the authentication in security context
                *Here SecurityContextHolder is a container which holds the security context of the application
            */
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            if( StringUtils.hasText(email) && jwtUtils.isTokenValid(token, userDetails) ){
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        try {
            /* 
                *Here we are continuing the filter chain to allow the request to proceed
                *doFilter is differed with doFilterInternal because doFilterInternal is specific to OncePerRequestFilter
                *but doFilter is general method in Filter interface and we are continuing the chain from there
            */
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        
        String tokenWithBearer = request.getHeader("Authorization");
        if( tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ") ){
            return tokenWithBearer.substring(7);
        }
        return null;
    }


}
