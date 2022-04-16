package com.moon.senla.educational_website.dao;

import com.moon.senla.educational_website.model.Theory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheoryRepository extends MongoRepository<Theory, String> {

    Page<Theory> findAll(Pageable pageable);

    void deleteById(String id);


    //    @Query("select t from Theory t where (t.name like :name or :name IS NULL) and (t.topic.name like :topic or :topic IS NULL) and (t.user.username like :username or :username IS NULL)")
//    Page<Theory> findAllTheoryByParam(Pageable pageable, @Param("name") String name,
//        @Param("topic") String topic,
//        @Param("username") String username);
}
