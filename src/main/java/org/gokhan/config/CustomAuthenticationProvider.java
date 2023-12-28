package org.gokhan.config;

import lombok.RequiredArgsConstructor;
import org.gokhan.model.User;
import org.gokhan.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = (User) userDetailsService.loadUserByUsername(username);
        if (passwordIsValid(user.getPassword(), password)) {
            return new UsernamePasswordAuthenticationToken(username, password);
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean passwordIsValid(String encodedPassword, String rawPassword) {
        return encodedPassword.equals(rawPassword);
    }
}