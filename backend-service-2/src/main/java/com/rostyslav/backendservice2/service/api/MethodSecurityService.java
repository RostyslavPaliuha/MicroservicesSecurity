package com.rostyslav.backendservice2.service.api;

import org.springframework.security.access.prepost.PreAuthorize;

public interface MethodSecurityService {

    @PreAuthorize("hasRole('ADMIN')")
    String getAdminData();

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    String getUserData();

    String getGuestData();

}
