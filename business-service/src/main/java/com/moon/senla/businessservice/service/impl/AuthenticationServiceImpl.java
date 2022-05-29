package com.moon.senla.businessservice.service.impl;

import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_SAVED;
import static com.moon.senla.businessservice.utils.StringConstants.COULD_NOT_UPDATED;

import com.moon.senla.businessservice.dao.RoleRepository;
import com.moon.senla.businessservice.dao.UserRepository;
import com.moon.senla.businessservice.error.CustomException;
import com.moon.senla.businessservice.error.NotFoundException;
import com.moon.senla.businessservice.error.ValidationException;
import com.moon.senla.businessservice.model.Role;
import com.moon.senla.businessservice.model.Status;
import com.moon.senla.businessservice.model.User;
import com.moon.senla.businessservice.model.dto.user.AuthenticationRequestDto;
import com.moon.senla.businessservice.security.jwt.JwtTokenProvider;
import com.moon.senla.businessservice.service.AuthenticationService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(JwtTokenProvider jwtTokenProvider,
        RoleRepository roleRepository,
        BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Map<String, String> login(AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        if (userRepository.findByUsername(username) == null) {
            throw new NotFoundException("User with username: " + username + ", not found");
        }
        User user = userRepository.findByUsername(username);
        if (user.getStatus().equals(Status.DELETED)) {
            throw new CustomException(
                "You cannot log in with this username, because your account has been deleted");
        }
        try {
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return response;
        } catch (Exception e) {
            throw new ValidationException("Invalid username or password");
        }
    }


    public User register(User user) {
        try {
            Role roleUser = roleRepository.findByName("ROLE_USER");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(roleUser);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(userRoles);
            user.setStatus(Status.ACTIVE);
            User registeredUser = userRepository.save(user);
            log.info("register - register user id: {} successfully registered",
                registeredUser.getId());

            return registeredUser;
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_SAVED.value);
        }
    }

    public User update(Principal principal, User user) {
        try {
            User oldUser = userRepository.findByUsername(principal.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
            oldUser.setFirstName(user.getFirstName());
            oldUser.setLastName(user.getLastName());
            oldUser.setStatus(user.getStatus());
            User updatedUser = userRepository.save(oldUser);
            log.info("update - update user id: {} successfully updated", updatedUser.getId());

            return updatedUser;
        } catch (Exception e) {
            throw new ValidationException(COULD_NOT_UPDATED.value);
        }
    }
}
