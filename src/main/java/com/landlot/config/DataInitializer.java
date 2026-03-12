package com.landlot.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.landlot.entity.User;
import com.landlot.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("ADMIN");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(com.landlot.enums.Role.ADMIN);
            admin.setActive(true);
            userRepository.save(admin);
        }
    }

}
