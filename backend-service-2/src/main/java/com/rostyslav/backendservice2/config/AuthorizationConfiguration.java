package com.rostyslav.backendservice2.config;

import com.rostyslav.utils.JwtAuthorizationFilter;
import com.rostyslav.utils.JwtAuthorizationPropertyHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthorizationConfiguration {

    @Bean
    public JwtAuthorizationPropertyHolder jwtAuthorizationPropertyHolder() {
        JwtAuthorizationPropertyHolder jwtAuthorizationPropertyHolder = new JwtAuthorizationPropertyHolder();
        jwtAuthorizationPropertyHolder.setSkipPaths(List.of("/actuator", "/error"));
        return jwtAuthorizationPropertyHolder;
    }

    @Bean
    @Primary
    public AuthorizationFilter getAuthorizationFilter(AuthorizationManager authorizationManager, JwtAuthorizationPropertyHolder authorizationPropertyHolder) {
        return new JwtAuthorizationFilter(authorizationManager, authorizationPropertyHolder);
    }

    @Bean
    public AuthorizationManager getAuthenticated() {
        return new AuthenticatedAuthorizationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChainAuthorization(HttpSecurity http, AuthorizationFilter jwtAuthorizationFilter) throws Exception {
        return http
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/api/*/books").authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((HttpServletRequest request, HttpServletResponse rsp, AuthenticationException e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}