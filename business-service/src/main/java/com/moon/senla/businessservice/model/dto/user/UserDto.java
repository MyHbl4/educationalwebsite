package com.moon.senla.businessservice.model.dto.user;

import com.moon.senla.businessservice.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Status status = Status.ACTIVE;
}
