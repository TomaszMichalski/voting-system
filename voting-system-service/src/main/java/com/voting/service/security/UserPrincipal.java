package com.voting.service.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voting.model.RoleName;
import com.voting.model.Voter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    @Getter
    @NonNull
    private final long id;

    @NonNull
    private final String name;

    @NonNull
    @JsonIgnore
    private final String email;

    @NonNull
    @JsonIgnore
    private final String password;

    @NonNull
    private final Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(Voter voter) {
        List<GrantedAuthority> authorities = voter.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                voter.getId(),
                voter.getName(),
                voter.getEmail(),
                voter.getPassword(),
                authorities
        );
    }

    public boolean isAdmin() {
        return authorities.stream().anyMatch(e -> e.getAuthority().equals(RoleName.ADMIN.name()));
    }

    @Override
    public String getUsername() {
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
