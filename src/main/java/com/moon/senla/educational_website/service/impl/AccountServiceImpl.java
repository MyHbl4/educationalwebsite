package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.model.AuthUser;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.service.AccountService;
import com.moon.senla.educational_website.util.ValidationUtil;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User get(AuthUser authUser) {
        return authUser.getUser();
    }

    @Override
    public void delete(AuthUser authUser) {
        userRepository.deleteById(authUser.id());
    }

    @Override
    public User register(User user) {
        ValidationUtil.checkNew(user);
        user.setRoles(Collections.singletonList(Role.USER));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void update(User user, AuthUser authUser) {
        User oldUser = authUser.getUser();
        ValidationUtil.assureIdConsistent(user, oldUser.getId());
        user.setRoles(oldUser.getRoles());
        if (user.getPassword() == null) {
            user.setPassword(oldUser.getPassword());
        }
        userRepository.save(user);
    }
}
