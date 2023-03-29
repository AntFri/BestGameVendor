package com.antoniofrische.bestgamevendor.security.models;

import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private UsuarioEntity user;

    public CustomUserDetails(UsuarioEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(user.getRole()));
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
        return user.isAccountActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isAccountActive();
    }

    @Override
    public boolean isEnabled() {
        return user.isAccountActive();
    }

    public String getFullName() {
        return user.getNombre() + " " + user.getApellido();
    }

}
