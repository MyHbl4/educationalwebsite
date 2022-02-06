package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAll(Pageable pageable);

    @Query(
        value =
            "select c.id, rating, price, user_id, name, topic_id from users u full join courses c ON c.user_id = u.id WHERE u.username = ?1",
        nativeQuery = true)
    Page<Course> findAllCoursesByUsername(Pageable pageable, String username);
}
