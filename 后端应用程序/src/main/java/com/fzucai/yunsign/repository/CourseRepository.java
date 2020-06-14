package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course ,Integer>{

    @Transactional
    @Modifying
    @Query("update Course c set c.coursename = :coursename, c.courseinfo = :courseinfo, c.courselocation = :courselocation, c.coursetime = :coursetime where c.courseid = :courseid")
    void updateCourseById(@Param("courseid")Integer courseid, @Param("coursename")String coursename, @Param("courseinfo")String courseinfo, @Param("courselocation")String courselocation, @Param("coursetime")String coursetime);
}
