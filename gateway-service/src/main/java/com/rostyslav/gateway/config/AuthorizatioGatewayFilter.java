package com.rostyslav.gateway.config;

import com.rostyslav.utils.JwtAuthorizationPropertyHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizatioGatewayFilter implements GatewayFilter {

    @Autowired
    private JwtAuthorizationPropertyHolder propertyHolder;

    @Override
    public Mono<Void> filter(ServerWebExchange webExchange, GatewayFilterChain chain) {
        return Mono.just(webExchange)
                .flatMap(exchange -> {
                    ServerHttpRequest serverHttpRequest = exchange.getRequest();
                    ServerHttpResponse response = exchange.getResponse();
                    try {
                    String authorization = serverHttpRequest.getHeaders().getOrEmpty("Authorization").get(0);
                        if (authorization != null && authorization.startsWith("Bearer ")) {
                            String token = authorization.replace("Bearer ", "");
                            Claims claims = (Claims) Jwts.parserBuilder()
                                    .setSigningKey(propertyHolder.getSignKey().getBytes())
                                    .build()
                                    .parse(token)
                                    .getBody();
                           return chain.filter(exchange);
                        } else {
                            return unauthorized(chain, exchange, response);
                        }
                    } catch (Exception e) {
                        return unauthorized(chain, exchange, response);
                    }
                });
    }
    private static Mono<Void> unauthorized(GatewayFilterChain chain, ServerWebExchange exchange, ServerHttpResponse response) {
        response.setStatusCode(HttpStatusCode.valueOf(401));
       return Mono.empty();
    }
}
