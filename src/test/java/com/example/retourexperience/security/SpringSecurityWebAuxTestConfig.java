/*
package com.example.retourexperience.security;

import com.example.retourexperience.ui.model.entity.UserRest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User("1", "user@company.com", "password","username", new HashSet<>(),
                true,true,true,"USER");
        UserActive basicActiveUser = new UserActive(basicUser, Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("PERM_FOO_READ")
        ));

        User managerUser = new UserImpl("Manager User", "manager@company.com", "password");
        UserActive managerActiveUser = new UserActive(managerUser, Arrays.asList(
                new SimpleGrantedAuthority("ROLE_MANAGER"),
                new SimpleGrantedAuthority("PERM_FOO_READ"),
                new SimpleGrantedAuthority("PERM_FOO_WRITE"),
                new SimpleGrantedAuthority("PERM_FOO_MANAGE")
        ));

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicActiveUser, managerActiveUser
        ));
    }
}*/
