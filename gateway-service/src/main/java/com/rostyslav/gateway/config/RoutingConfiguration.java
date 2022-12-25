package com.rostyslav.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {

    @Autowired
    private GatewayFilter authorizationGatewayFilter;

    @Bean
    public RouteLocator authenticationRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/login")
                        .uri("lb://authentication-service"))
                .build();
    }

    @Bean
    public RouteLocator booksRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/books")
                       /* .filters(spec -> {
                            //spec.rewritePath("/books", "/api/v1/books");
                           // spec.filter(authorizationGatewayFilter);
                            return spec;
                        })*/
                        .uri("lb://book-service")).build();
    }
}
