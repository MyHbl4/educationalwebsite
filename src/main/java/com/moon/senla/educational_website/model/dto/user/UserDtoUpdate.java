package com.moon.senla.educational_website.model.dto.user;

import com.moon.senla.educational_website.model.Status;
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
