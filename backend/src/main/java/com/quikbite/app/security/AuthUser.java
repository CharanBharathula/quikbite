package com.quikbite.app.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.quikbite.app.auth_users.model.User;

import lombok.Builder;
import lombok.Data;
/**
 * AuthUser class implementing UserDetails for Spring Security integration.
 * Internally UserDetails is used by Spring Security to represent the authenticated user.
 * Its a core interface which provides user information to Spring Security.
 * Here the User Entity is feed into UserDetails through composition.
 */

@Builder
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
public class AuthUser implements UserDetails {
    User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList();
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
    public boolean isEnabled() {
        return user.isActive();
    }

}
