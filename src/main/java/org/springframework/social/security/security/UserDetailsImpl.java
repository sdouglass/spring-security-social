package org.springframework.social.security.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.model.User;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private User user;
    private List<GrantedAuthority> roles;

    public UserDetailsImpl(User user, List<GrantedAuthority> roles) {
        this.user = user;
        this.roles = roles;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
