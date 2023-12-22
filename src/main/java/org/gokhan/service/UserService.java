package org.gokhan.service;

import org.gokhan.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(User user);

    Optional<User> findByUsername(String email);
}
