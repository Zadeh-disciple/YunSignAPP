package com.fzucai.yunsign.service;


import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.repository.CourseRepository;
import com.fzucai.yunsign.repository.Course_userRepository;
import com.fzucai.yunsign.repository.PutSignRepository;
import com.fzucai.yunsign.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    Course_userRepository course_userRepository;
    @Autowired
    SignRepository signRepository;
    @Autowired
    PutSignRepository putSignRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public String addCourse(Course course){
        courseRepository.save(course);
        return "addSuccess";
    }

    public String deleteCourse(Integer courseid){
        course_userRepository.deleteBycourseId(courseid);
        System.out.println("111");
        signRepository.deleteBycourseId(courseid);
        System.out.println("222");
        putSignRepository.deleteBycourseId(courseid);
        System.out.println("333");
        courseRepository.deleteById(courseid);
        return "deletesuccess";
    }

    public String updateCourse(Course course){
        courseRepository.updateCourseById(course.courseid,course.coursename,course.courseinfo,course.classid,course.semester);
        return "updatesuccess";
    }
    public Course findById(Integer courseid) {
        return courseRepository.findById(courseid).get();
    }
}
