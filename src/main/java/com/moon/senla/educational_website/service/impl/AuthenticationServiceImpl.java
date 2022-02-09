package com.moon.senla.educational_website.service.impl;

import com.moon.senla.educational_website.dao.RoleRepository;
import com.moon.senla.educational_website.dao.UserRepository;
import com.moon.senla.educational_website.error.CustomException;
import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Status;
import com.moon.senla.educational_website.model.User;
import com.moon.senla.educational_website.model.dto.mapper.UserMapper;
import com.moon.senla.educational_website.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.educational_website.model.dto.user.UserDtoUpdate;
import com.moon.senla.educational_website.model.dto.user.UserNewDto;
import com.moon.senla.educational_website.security.jwt.JwtTokenProvider;
import com.moon.senla.educational_website.service.AuthenticationService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ ClassName  :AuthenticationServiceImpl
 * @ Author     :gmoon
 * @ Description:
 */

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(
        AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider, RoleRepository roleRepository,
        BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Map<Object, Object> login(AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(
                    "User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return response;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid username or password");
        }
    }


    public User register(UserNewDto userNew) {
        try {
            User user = UserMapper.INSTANCE.userNewDtoToUser(userNew);
            Role roleUser = roleRepository.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(userRoles);
            user.setStatus(Status.ACTIVE);
            User registeredUser = userRepository.save(user);
            log.info("Register - user id: {} successfully registered", registeredUser.getId());

            return registeredUser;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, user could not be saved");
        }
    }

    public User update(Principal principal, UserDtoUpdate updateUser) {
        try {
            User user = UserMapper.INSTANCE.userDtoUpdateToUser(updateUser);
            User oldUser = userRepository.findByUsername(principal.getName());
            Role roleUser = roleRepository.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            user.setUsername(oldUser.getUsername());
            user.setId(oldUser.getId());
            user.setRoles(userRoles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setStatus(Status.ACTIVE);
            User updatedUser = userRepository.save(user);
            log.info("Update - user id: {} successfully updated", updatedUser.getId());

            return updatedUser;
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                "Invalid request, update could not be saved");
        }
    }
}
