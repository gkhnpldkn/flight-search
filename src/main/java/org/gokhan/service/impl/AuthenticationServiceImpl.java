package org.gokhan.service.impl;

import lombok.RequiredArgsConstructor;
import org.gokhan.dto.request.SignUpRequest;
import org.gokhan.dto.request.SigninRequest;
import org.gokhan.dto.response.JwtAuthenticationResponse;
import org.gokhan.model.Role;
import org.gokhan.model.User;
import org.gokhan.service.AuthenticationService;
import org.gokhan.service.JwtService;
import org.gokhan.service.RoleService;
import org.gokhan.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Set<Role> roles = getRoleList(request.getRoles());
        User user = User.builder()
                .username(request.getEmail())
                .encryptedPassword(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        user = userService.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        authenticationManager.authenticate(token);
        Optional<User> userOptional = userService.findByUsername(request.getEmail());
        User user = userOptional.orElseThrow(() -> new IllegalArgumentException("Geçersiz email veya şifre."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private Set<Role> getRoleList(List<String> roleIds) {
        List<Role> roles = roleService.findAllByIds(roleIds);
        return new HashSet<>(roles);
    }
}
