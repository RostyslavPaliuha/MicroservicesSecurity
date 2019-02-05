package com.rostyslav.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        if (request.getServletPath().startsWith("/api") & !request.getServletPath()
                .equals("/api/guest")) {
            String token = request.getHeader("Authorization");
            try {
                if (token != null & token.startsWith("Bearer ")) {
                    token = token.replace("Bearer ", "");
                    Claims claims = Jwts.parser()
                            .setSigningKey("otherpeopledontknowit".getBytes())
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
                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not found");
                SecurityContextHolder.clearContext();
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
