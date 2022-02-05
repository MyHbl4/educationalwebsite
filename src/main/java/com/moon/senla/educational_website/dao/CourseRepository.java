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

//    @Query(
//        value =
//            "select id, email, username, first_name, last_name, password, status, group_id, user_id from Users u full join user_group ug ON u.id = ug.user_id WHERE ug.group_id = (:group_id)",
//        nativeQuery = true)
    Page<Course> getCoursesByUser_Id(Pageable pageable, Long userId);
}
