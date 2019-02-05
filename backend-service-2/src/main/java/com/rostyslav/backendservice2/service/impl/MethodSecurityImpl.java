package com.rostyslav.backendservice2.service.impl;

import com.rostyslav.backendservice2.service.api.MethodSecurityService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MethodSecurityImpl implements MethodSecurityService {

    @Value("${myProperty: default my property value}")
    private String myProperty;

    @Override
    public String getAdminData() {
        return "Hello Admin! your property is: " + myProperty;
    }

    @Override
    public String getUserData() {
        return "Hello User! your property is: " + myProperty;
    }

    @Override
    public String getGuestData() {
        return "Hello Guest!";
    }
}
