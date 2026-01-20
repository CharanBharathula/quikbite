package com.quikbite.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.quikbite.app.auth_users.model.User;
import lombok.RequiredArgsConstructor;
import com.quikbite.app.auth_users.repository.UserRepository;
import com.quikbite.app.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new NotFoundException("User not found with email: " + username));
        return AuthUser.builder()
            .user(user)
            .build();
    }

}
