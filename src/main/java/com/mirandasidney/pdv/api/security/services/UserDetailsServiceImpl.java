package com.mirandasidney.pdv.api.security.services;

import com.mirandasidney.pdv.api.entities.User;
import com.mirandasidney.pdv.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user =  Optional.ofNullable(repository.findUserByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return UserDetailsImpl.build(user);
    }
}
