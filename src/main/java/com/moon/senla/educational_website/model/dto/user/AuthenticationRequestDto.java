package com.moon.senla.educational_website.model.dto.user;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
