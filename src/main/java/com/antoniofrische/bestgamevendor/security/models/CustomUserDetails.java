package com.antoniofrische.bestgamevendor.security.models;

import com.antoniofrische.bestgamevendor.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails, Serializable {

    private UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole());
        auths.add(auth);
        return auths;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getAccountActive();
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountActive();
    }

}
