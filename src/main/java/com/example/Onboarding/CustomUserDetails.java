package com.example.Onboarding;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor to initialize the user details
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Getters for the properties
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // If you want to manage account expiration, modify this
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Modify if you want to implement account locking
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Modify if you want to implement credentials expiration
    }

    @Override
    public boolean isEnabled() {
        return true;  // Modify if you want to implement user account enabling/disabling
    }
}
