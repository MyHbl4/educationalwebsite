package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import java.util.Map;

public interface AuthenticationService {

    Map<Object, Object> login(AuthenticationRequestDto requestDto);

    User register(User user);
}
