package com.dmitry.NewsClient.config;

import java.util.UUID;

import com.dmitry.NewsClient.entity.UserEntity;
import com.dmitry.NewsClient.repository.RepositoryUser;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final RepositoryUser userRepo;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findById(UUID.fromString(username));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }
}
