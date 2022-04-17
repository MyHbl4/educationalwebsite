package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.Theory;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheoryService {

    Theory save(Principal principal, Theory theory);

    Theory update(Principal principal, Theory theory);

    Theory findById(String id);

    void deleteById(String id);

    Page<Theory> findAll(Pageable pageable);

    Page<Theory> findAllTheoryByParam(Pageable pageable, String name, String topic, String user);
}

