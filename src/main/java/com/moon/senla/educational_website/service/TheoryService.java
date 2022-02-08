package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import com.moon.senla.educational_website.model.dto.theory.TheoryNewDto;
import com.moon.senla.educational_website.model.dto.theory.TheoryUpdateDto;
import java.security.Principal;

public interface TheoryService {

    Theory save(Principal principal, TheoryNewDto theory);

    Theory update(Principal principal, TheoryUpdateDto theory);

    Theory findById(long id);

    void deleteById(long id);
}

