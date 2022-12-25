package com.rostyslav.utils;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


@Data
public class JwtAuthenticationConfig {

    @Value("${security.jwt.url:/login}")
    private String url;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration; // 24 hours

    @Value("${security.jwt.signKey:otherpeopledontknowit1234567890!@#$}")
    private String secret;

    private List<String> skipPaths;
}
