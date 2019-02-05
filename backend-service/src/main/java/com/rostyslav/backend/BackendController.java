package com.rostyslav.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RefreshScope
@RequestMapping("/api")
public class BackendController {

    @Value("${adminProperty: default admin property value}")
    private String adminProperty;
    @Value("${userProperty: default user property value}")
    private String userProperty;

    @GetMapping("/admin")
    public ResponseEntity admin() {
        String data = "{\"data\":\"Hello Admin! your property is: " + adminProperty + "\"}";
        return new ResponseEntity(data, createHeaders(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity user() {
        String userData = "{\"data\":\"Hello User! your property is: " + userProperty + "\"}";
        return new ResponseEntity(userData, createHeaders(), HttpStatus.OK);
    }

    @GetMapping("/guest")
    public ResponseEntity guest() {
        return new ResponseEntity("{\"data\":\"Hello Guest!\"}", createHeaders(), HttpStatus.OK);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.ALL));
        return headers;
    }
}

