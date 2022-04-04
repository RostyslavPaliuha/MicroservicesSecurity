package com.rostyslav.auth.repository;

import com.rostyslav.auth.repository.entity.AccountAuthenticationDetailsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Account auth repository.
 */
@Repository
public interface AccountAuthDetailsRepository extends CrudRepository<AccountAuthenticationDetailsEntity, String> {

    AccountAuthenticationDetailsEntity findByLoginName(String loginName);

    boolean existsByLoginName(String loginName);
}
