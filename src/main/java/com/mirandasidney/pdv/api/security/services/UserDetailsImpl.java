package com.mirandasidney.pdv.api.security.services;

import com.mirandasidney.pdv.api.entities.User;
import com.mirandasidney.pdv.api.utils.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final Boolean STATUS = true;
    private UUID uuid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phone;
    private Collection<? extends GrantedAuthority> authorities;
    private String createdAt;
    private String updated;
    private Boolean active = STATUS;

    private UserDetailsImpl(UUID uuid, String firstname, String lastname, String username, String password, String phone,
                           Collection<? extends GrantedAuthority> authorities, String updated, Boolean active) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.authorities = authorities;
        this.createdAt = DateUtils.getDateTime();
        this.updated = updated;
        this.active = active;
    }

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new UserDetailsImpl(
                user.getUuid(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                authorities,
                user.getUpdated(),
                user.getActive());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
