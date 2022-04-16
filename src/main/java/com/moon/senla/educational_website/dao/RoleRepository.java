package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByName(String name);
}
