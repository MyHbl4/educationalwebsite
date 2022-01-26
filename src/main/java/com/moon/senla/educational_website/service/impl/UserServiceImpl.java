package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.RoleRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Status;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
        BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        if (result == null) {
            log.warn("IN findByUsername - no user found by username: {}", username);
            return null;
        }
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(long id) {
        User user = null;
        Optional<User> option = userRepository.findById(id);
        if (option.isPresent()) {
            user = option.get();
        }
        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

}
