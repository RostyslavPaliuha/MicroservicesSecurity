package com.rostyslav.auth.service;

import com.rostyslav.auth.repository.AccountAuthDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Profile("devH2")
public class UserCredentialsService implements UserDetailsService {

    @Autowired
    private AccountAuthDetailsRepository accountAuthDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        return accountAuthDetailsRepository.findByLoginName(loginName);
    }
}

