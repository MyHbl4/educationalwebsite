package com.moon.senla.businessservice.service;

import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.model.dto.user.AuthenticationRequestDto;
import java.security.Principal;
import java.util.Map;

public interface AuthenticationService {

    Map<String, String> login(AuthenticationRequestDto requestDto);

    User register(User user);

    User update(Principal principal, User user);

}
