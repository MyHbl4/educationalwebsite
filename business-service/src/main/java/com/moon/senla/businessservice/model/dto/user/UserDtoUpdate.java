package com.moon.senla.businessservice.model.dto.user;

import com.moon.senla.businessservice.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoUpdate {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Status status = Status.ACTIVE;
}
