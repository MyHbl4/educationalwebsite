package com.moon.senla.educational_website.model.dto.group;

import com.moon.senla.educational_website.model.Group;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupPageDto {
    private List<Group> groups;
    private int currentPage;
    private int totalPages;
}
