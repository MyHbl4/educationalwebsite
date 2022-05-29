package com.moon.senla.businessservice.dao;

import com.moon.senla.businessservice.model.Theory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, Long> {

    Page<Theory> findAll(Pageable pageable);

    @Query("select t from Theory t where (t.name like :name or :name IS NULL) and (t.topic.name like :topic or :topic IS NULL) and (t.user.username like :username or :username IS NULL)")
    Page<Theory> findAllTheoryByParam(Pageable pageable, @Param("name") String name,
        @Param("topic") String topic,
        @Param("username") String username);
}
