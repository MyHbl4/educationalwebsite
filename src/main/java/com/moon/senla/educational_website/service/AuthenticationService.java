package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import java.security.Principal;
import java.util.Map;

public interface AuthenticationService {

    Map<String, String> login(AuthenticationRequestDto requestDto);

    User register(UserNewDto user);

    User update(Principal principal, UserDtoUpdate userNew);

}
