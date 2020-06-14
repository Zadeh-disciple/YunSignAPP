package com.fzucai.yunsign.service;


import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public String addCourse(Course course){
        courseRepository.save(course);
        return "addSuccess";
    }

    public String deleteCourse(Integer courseid){
        courseRepository.deleteById(courseid);
        return "deletesuccess";
    }

    public String updateCourse(Course course){
        courseRepository.updateCourseById(course.courseid,course.coursename,course.courseinfo,course.courselocation,course.coursetime);
        return "updatesuccess";
    }
}
