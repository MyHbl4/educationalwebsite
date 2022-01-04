package com.moon.senla.educational_website.model.dto.theory;

import com.moon.senla.educational_website.model.Theory;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TheoryPageDto {
    private List<Theory> theories;
    private int currentPage;
    private int totalPages;
}
