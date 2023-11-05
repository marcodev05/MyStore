package com.tsk.ecommerce.services.security;

import com.tsk.ecommerce.entities.auth.UserEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private String username;
    private String password;
    private UserEntity user;

    public LoginAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
    }

    public LoginAuthenticationToken(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.user = user;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return username;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.username = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof LoginAuthenticationToken))
            return false;
        LoginAuthenticationToken other = (LoginAuthenticationToken) obj;
        return Objects.equals(username, other.username);
    }

    public String getPassword() {
        return password;
    }
}
