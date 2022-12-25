package com.rostyslav.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class JwtAuthorizationPropertyHolder {
    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${security.jwt.signKey:otherpeopledontknowit1234567890!@#$}")
    private String signKey;

    private List<String> skipPaths;
}
