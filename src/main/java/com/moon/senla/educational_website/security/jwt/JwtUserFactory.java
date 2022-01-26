package com.moon.senla.educational_website.security.jwt;

import com.moon.senla.educational_website.model.Role;
import com.moon.senla.educational_website.model.Status;
import com.moon.senla.educational_website.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
            user.getStatus().equals(Status.ACTIVE)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
            .map(role ->
                new SimpleGrantedAuthority(role.getName())
            ).collect(Collectors.toList());
    }
}
