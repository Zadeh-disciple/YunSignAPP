package com.fzucai.yunsign.controller;


import com.fzucai.yunsign.entity.School;
import com.fzucai.yunsign.entity.Xueyuan;
import com.fzucai.yunsign.entity.Zhuanye;
import com.fzucai.yunsign.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolHandler {
    @Autowired
    SchoolService schoolService;

    @GetMapping("/findAll")
    public List<School> findAll(){return schoolService.findAll();}

    //新建学校
    @RequestMapping(value = "/addSchool", method = RequestMethod.POST)
    public String addSchool(@RequestBody School school){
        return schoolService.addSchool(school);
    }

    //删除学校
    @DeleteMapping("/deleteSchool")
    public String deleteSchool(@RequestParam Integer schoolid){
        return schoolService.deleteSchool(schoolid);
    }

    //修改学校
    @RequestMapping(value = "/updateSchool", method = RequestMethod.POST)
    public String updateSchool(@RequestBody School school){
        return schoolService.updateSchool(school);
    }

//    目前不需要学院、专业表的增删改查
    @GetMapping("/findAllXueyuan")
    public List<Xueyuan> findAllXueyuan(){return schoolService.findAllXueyuan();}

    @GetMapping("/findAllZhuanye")
    public List<Zhuanye> findAllZhuanye(){return schoolService.findAllZhuanye();}
}
