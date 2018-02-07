package com.example.demo.configurations;

import com.example.demo.domains.User;
import com.example.demo.domains.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProjectUserDetails implements UserDetails {

    private final User user;
    private final Long userId;

    public ProjectUserDetails(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(" getAuth ");

        Set<GrantedAuthority> authoritySet = new HashSet<>();
        for (UserRole userRole : user.getUserRoles()) {
            System.out.println(" role : " + userRole.getRole());

            if (userRole.getEnabled()) {
                authoritySet.add(new SimpleGrantedAuthority(userRole.getRole().name()));
            }
        }
        return new ArrayList<>(authoritySet);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.getEnabled();
    }

    public Set<UserRole> userRoles() {
        return user.getUserRoles();
    }

    public User getUser() {
        return user;
    }

    public Long getUserId() {
        return userId;
    }
}
