package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import java.util.Map;

public interface AuthenticationService {

    Map<Object, Object> login(AuthenticationRequestDto requestDto);

    User register(UserNewDto user);

    User update(UserDtoUpdate userNew);

}
