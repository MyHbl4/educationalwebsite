package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {

    Page<Course> findAll(Pageable pageable);

    //    @Query(
//        value =
//            "select c.id, rating, price, user_id, name, topic_id from users u full join courses c ON c.user_id = u.id WHERE u.username = ?1",
//        nativeQuery = true)
//    Page<Course> findAllCoursesByUsername(Pageable pageable, String username);

    //    @Query("select c from Course c where (c.name like :name or :name IS NULL) and (c.topic.name like :topic or :topic IS NULL) and (c.user.username like :username or :username IS NULL)")
//    Page<Course> findAllCoursesByParam(Pageable pageable, @Param("name") String name,
//        @Param("topic") String topic,
//        @Param("username") String username);
    void deleteById(String id);
}
