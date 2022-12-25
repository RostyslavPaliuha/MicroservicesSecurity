package com.rostyslav.gateway.config;

import com.rostyslav.utils.JwtAuthorizationPropertyHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public JwtAuthorizationPropertyHolder getAuthorizationPropertyHolder() {
        return new JwtAuthorizationPropertyHolder();
    }
}
