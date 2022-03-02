package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import java.security.Principal;

public interface TheoryService {

    Theory save(Principal principal, Theory theory);

    Theory update(Principal principal, Theory theory);

    Theory findById(long id);

    void deleteById(long id);
}

