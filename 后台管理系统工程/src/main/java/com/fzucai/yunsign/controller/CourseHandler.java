package com.fzucai.yunsign.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzucai.yunsign.entity.Course;
import com.fzucai.yunsign.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseHandler {
    @Autowired
    CourseService courseService;
    //查询所有课程

    @GetMapping("/findAll")
    public List<Course> findAll(){
        return courseService.findAll();
    }

    //根据课程id查找课程信息
    @PostMapping("/findById")
    public Course findById(@RequestBody JSONObject jsonObject){
        String a =jsonObject.get("courseid").toString();
        Integer courseid = Integer.valueOf(a);
        Course course =courseService.findById(courseid);
        return course;
    }
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    //删除
    @PostMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Integer courseid){
        return courseService.deleteCourse(courseid);
    }

    //修改
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    public String updateCourse(@RequestBody Course course){
        return courseService.updateCourse(course);
    }
}
