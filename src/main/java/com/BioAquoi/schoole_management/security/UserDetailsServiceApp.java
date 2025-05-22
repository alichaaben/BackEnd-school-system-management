package com.BioAquoi.schoole_management.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.BioAquoi.schoole_management.entity.User;
import com.BioAquoi.schoole_management.repository.UserRepo;

import java.util.Collections;

@Service
public class UserDetailsServiceApp implements UserDetailsService {

    private final UserRepo appUserRepo;

    public UserDetailsServiceApp(UserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = appUserRepo.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(authority) // Liste des autorités
        );
    }
}
