package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.entity.Course_user;
import com.fzucai.yunsign.entity.Role;
import com.fzucai.yunsign.service.Course_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Course_user")
public class Course_userHandler {
    @Autowired
    private Course_userService course_userService;

    @GetMapping("/findAll")
    public List<Course_user> findAll() {return course_userService.findAll();}

    //新建
    @RequestMapping(value = "/addCourse_user", method = RequestMethod.POST)
    public String addCourse_user(@RequestBody Course_user course_user){
        return course_userService.addCourse_user(course_user);
    }

    //删除
    @PostMapping("/deleteCourse_user")
    public String deleteRole(@RequestParam Integer cuid){
        return course_userService.deleteCourse_user(cuid);
    }

    //改
    @RequestMapping(value = "/updateCourse_user", method = RequestMethod.POST)
    public String updateCourse_user(@RequestBody Course_user course_user){
        return course_userService.updateCourse_user(course_user);
    }
}
