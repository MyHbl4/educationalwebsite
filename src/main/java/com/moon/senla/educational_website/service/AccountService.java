package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.AuthUser;
import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import java.util.List;

public interface AccountService {

    User get(AuthUser authUser);

    void delete(AuthUser authUser);

    User register(User user);

    void update(User user, AuthUser authUser);
}
