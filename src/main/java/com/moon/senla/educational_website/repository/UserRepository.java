package com.moon.senla.educational_website.repository;

import com.moon.senla.educational_website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
