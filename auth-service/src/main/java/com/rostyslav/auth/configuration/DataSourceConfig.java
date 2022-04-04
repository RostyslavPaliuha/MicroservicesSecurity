package com.rostyslav.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class DataSourceConfig {

   /* @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();

        contextSource.setUrl(env.getRequiredProperty("ldap.url"));
        contextSource.setBase(
                env.getRequiredProperty("ldap.partitionSuffix"));
        contextSource.setUserDn(
                env.getRequiredProperty("ldap.principal"));
        contextSource.setPassword(
                env.getRequiredProperty("ldap.password"));

        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }*/
}
