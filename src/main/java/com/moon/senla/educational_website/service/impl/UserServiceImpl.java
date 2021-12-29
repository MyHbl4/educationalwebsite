package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
