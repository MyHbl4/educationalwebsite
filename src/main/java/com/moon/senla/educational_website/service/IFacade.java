package com.moon.senla.educational_website.service;

import com.moon.senla.educational_website.model.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFacade {

    Page<UserDto> findAllUsersByParam(Pageable pageable, String firstName, String lastName);
}
