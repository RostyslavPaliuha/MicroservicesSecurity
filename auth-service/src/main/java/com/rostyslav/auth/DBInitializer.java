package com.rostyslav.auth;

import com.rostyslav.auth.repository.AccountAuthDetailsRepository;
import com.rostyslav.auth.repository.entity.AccountAuthenticationDetailsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private AccountAuthDetailsRepository accountAuthDetailsRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Initialize DB with new admin account");
        AccountAuthenticationDetailsEntity accountAuthenticationDetailsEntity = new AccountAuthenticationDetailsEntity();
        accountAuthenticationDetailsEntity.setLoginName("rostyslav");
        accountAuthenticationDetailsEntity.setPassword("password");
        accountAuthenticationDetailsEntity.setAuthority("ADMIN");
        accountAuthenticationDetailsEntity.setEnabled(true);
        accountAuthenticationDetailsEntity.setNotExpired(true);
        accountAuthenticationDetailsEntity.setFirstName("Rostyslav");
        accountAuthenticationDetailsEntity.setLastName("Paliuha");
        accountAuthenticationDetailsEntity.setNotLocked(true);
        addNewInitialAccount(accountAuthenticationDetailsEntity);
    }

    /**
     * If account not exists it will be added as new admin.
     *
     * @param accountAuthenticationDetailsEntity - admin entity.
     */
    private void addNewInitialAccount(AccountAuthenticationDetailsEntity accountAuthenticationDetailsEntity) {
        if (!accountAuthDetailsRepository.existsByLoginName("rostyslav")) {
            accountAuthDetailsRepository.save(accountAuthenticationDetailsEntity);
        }
    }

}
