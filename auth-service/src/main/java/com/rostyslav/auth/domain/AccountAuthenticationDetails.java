package com.rostyslav.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Reflects account auth details domain.
 */
@Data
public class AccountAuthenticationDetails implements UserDetails {

    private String id;

    private String firstName;

    private String lastName;

    private String loginName;

    private String password;

    private String authority;

    private boolean isExpired;

    private boolean isEnabled;

    private boolean isLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
