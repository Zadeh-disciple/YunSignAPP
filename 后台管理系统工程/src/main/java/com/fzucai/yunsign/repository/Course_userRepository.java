package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.entity.Course_user;
import com.fzucai.yunsign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface Course_userRepository extends JpaRepository<Course_user, Integer> {

    @Transactional
    @Modifying
    @Query("update Course_user c set c.totalexp = :totalexp, c.course = :course, c.user = :user where c.cuid = :cuid")
    void updateCourse_userById(@Param("cuid")Integer cuid, @Param("totalexp")Integer totalexp, @Param("course") Course course, @Param("user") User user);

    @Transactional
    @Modifying
    @Query("select c from Course_user c  where c.user.userid = :userid")
    List<Course_user> findcourseByuserId(@Param("userid")Integer userid);

    @Transactional
    @Modifying
    @Query("select c from Course_user c  where c.course.courseid = :courseid")
    List<Course_user> findexpBycourseId(@Param("courseid")Integer courseid);

    @Transactional
    @Modifying
    @Query("delete from Course_user c  where c.user.userid = :userid")
    void deleteByuserId(@Param("userid")Integer userid);

    @Transactional
    @Modifying
    @Query("delete from Course_user c  where c.course.courseid = :courseid")
    void deleteBycourseId(@Param("courseid")Integer courseid);
}
