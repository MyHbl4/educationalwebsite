package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
