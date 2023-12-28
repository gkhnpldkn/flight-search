package org.gokhan.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gokhan.model.User;
import org.gokhan.repository.UserRepository;
import org.gokhan.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı!"));
    }

    @Override
    public User save(User user) {
        user = userRepository.save(user);
        log.info("saved user. {}", user);
        log.info("{} bilgisayar {} öğrencidir.", "gökhan", "mühendisliğinde");

        return user;
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepository.findByUsername(email);
    }
}
