package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

    Page<Course> findAll(Pageable pageable);

    Page<Course> findAllCoursesByUser_Username(Pageable pageable, String username);

    //    @Query("select c from Course c where (c.name like :name or :name IS NULL) and (c.topic.name like :topic or :topic IS NULL) and (c.user.username like :username or :username IS NULL)")
//    Page<Course> findAllCoursesByParam(Pageable pageable, @Param("name") String name,
//        @Param("topic") String topic,
//        @Param("username") String username);
    void deleteById(String id);
}
