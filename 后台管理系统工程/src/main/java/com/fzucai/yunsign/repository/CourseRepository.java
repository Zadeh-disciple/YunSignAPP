package com.fzucai.yunsign.repository;

import com.fzucai.yunsign.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CourseRepository extends JpaRepository<Course ,Integer>{

    @Transactional
    @Modifying
    @Query("update Course c set c.coursename = :coursename, c.courseinfo = :courseinfo, " +
            "c.classid = :classid, c.semester = :semester where c.courseid = :courseid")
    void updateCourseById(@Param("courseid")Integer courseid, @Param("coursename")String coursename, @Param("courseinfo")String courseinfo, @Param("classid")String classid, @Param("semester")String semester);
}
