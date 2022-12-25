package com.rostyslav.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends AuthorizationFilter {

    private JwtAuthorizationPropertyHolder propertyHolder;
    /**
     * Creates an instance.
     *
     * @param authorizationManager the {@link AuthorizationManager} to use
     */
    public JwtAuthorizationFilter(AuthorizationManager<HttpServletRequest> authorizationManager, JwtAuthorizationPropertyHolder jwtAuthenticationConfig) {
        super(authorizationManager);
        this.propertyHolder = jwtAuthenticationConfig;
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String servletPath = httpServletRequest.getServletPath();
        if (propertyHolder.getSkipPaths().stream().noneMatch(servletPath::contains)) {
            try {
                String token = httpServletRequest.getHeader("Authorization");
                if (token != null && token.startsWith("Bearer ")) {
                    token = token.replace("Bearer ", "");
                    Claims claims = Jwts.parser()
                            .setSigningKey(propertyHolder.getSignKey().getBytes())
                            .parseClaimsJws(token)
                            .getBody();
                    String username = claims.getSubject();
                    @SuppressWarnings("unchecked")
                    List<String> authorities = claims.get("authorities", List.class);
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
                    for (String authority : authorities) {
                        grantedAuthorities.add(new SimpleGrantedAuthority(authority));
                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, grantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(servletRequest, httpServletResponse);
                } else {
                    throw new RuntimeException("Token not found.");
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                chain.doFilter(servletRequest, httpServletResponse);
            }
        } else {
            chain.doFilter(servletRequest, httpServletResponse);
        }
    }
}
