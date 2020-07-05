package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SignRepository extends JpaRepository<Sign, Integer> {


    @Transactional
    @Modifying
    @Query("delete from Sign c  where c.user.userid = :userid")
    void deleteByuserId(@Param("userid")Integer userid);

    @Transactional
    @Modifying
    @Query("delete from Sign c  where c.course.courseid = :courseid")
    void deleteBycourseId(@Param("courseid")Integer courseid);
}
