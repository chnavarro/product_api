package com.tizo.productapi.service;

import com.tizo.productapi.model.User;
import com.tizo.productapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optUser = repository.findByUserName(username);
        if (!optUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(optUser.get());
    }
}
