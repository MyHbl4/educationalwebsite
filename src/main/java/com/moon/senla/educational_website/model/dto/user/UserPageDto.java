package com.moon.senla.educational_website.model.dto.user;

import com.moon.senla.educational_website.model.User;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserPageDto {
    private List<User> users;
    private int currentPage;
    private int totalPages;
}
