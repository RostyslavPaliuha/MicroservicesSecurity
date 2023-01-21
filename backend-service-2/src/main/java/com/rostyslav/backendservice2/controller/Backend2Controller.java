package com.rostyslav.backendservice2.controller;

import com.rostyslav.backendservice2.service.api.MethodSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/v1")
public class Backend2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Backend2Controller.class);
    @Autowired
    private MethodSecurityService methodSecurityService;

    @GetMapping("/admin")
    public String admin() {
        logger.info("Enter admin url");
        return methodSecurityService.getAdminData();
    }

    @GetMapping("/user")
    public String user() {
        logger.info("Enter user url");
        return methodSecurityService.getUserData();
    }

    @GetMapping("/guest")
    public String guest() {
        logger.info("Enter guest url");
        return methodSecurityService.getGuestData();
    }
}

