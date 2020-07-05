package com.fzucai.yunsign.service;

import com.fzucai.yunsign.entity.Course_user;
import com.fzucai.yunsign.repository.Course_userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Course_userService {
    @Autowired
    Course_userRepository course_userRepository;

    public List<Course_user> findAll(){
        return course_userRepository.findAll();
    }

    public List<Course_user> findcourseByuserId(Integer userid){
        return course_userRepository.findcourseByuserId(userid);
    }

    public List<Course_user> findexpBycourseId(Integer courseid) {
        return course_userRepository.findexpBycourseId(courseid);
    }

    public String addCourse_user(Course_user course_user){
        course_userRepository.save(course_user);
        return "addCourse_userSuccess";
    }

    public String deleteCourse_user(Integer cuid){
        course_userRepository.deleteById(cuid);
        return "deletesuccess";
    }

    public String updateCourse_user(Course_user course_user){
        course_userRepository.updateCourse_userById(course_user.cuid,course_user.totalexp,course_user.course,course_user.user);
        return "updatesuccess";
    }
}
