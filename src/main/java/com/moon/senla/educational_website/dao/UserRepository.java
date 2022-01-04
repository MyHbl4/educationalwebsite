package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Course;
import com.moon.senla.educational_website.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
        Optional<User> findByEmailIgnoreCase(@Param("email") String email);

    Page<User> findAll(Pageable pageable);

}

