package com.dmitry.NewsClient.config;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.exeption.CustomException;
import com.dmitry.NewsClient.exeption.ErrorCodes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private String id;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserEntity userEntity) {
        if (userEntity == null) {
            throw new CustomException(ErrorCodes.TOKEN_NOT_PROVIDED);
        }
        CustomUserDetails c = new CustomUserDetails();
        c.id = userEntity.getId();
        c.email = userEntity.getEmail();
        c.password = userEntity.getPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getEmail()));
        return c;
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}
