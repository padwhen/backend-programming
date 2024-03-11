package com.example.bookstore.service;

import com.example.bookstore.domain.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.bookstore.domain.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;
    @Autowired
    public UserDetailServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currUser = repository.findByUsername(username);
        UserBuilder builder = null;
        if (currUser == null) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(currUser.getPasswordHash());
            builder.roles(currUser.getRole());
        }
        return builder.build();
    }
}
