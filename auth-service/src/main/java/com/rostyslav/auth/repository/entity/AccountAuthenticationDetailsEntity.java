package com.rostyslav.auth.repository.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * Reflects account auth details.
 */
@Data
@Entity
public class AccountAuthenticationDetailsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String loginName;

    private String password;

    private String authority;

    private boolean isNotExpired;

    private boolean isEnabled;

    private boolean isNotLocked;

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
        return isNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
