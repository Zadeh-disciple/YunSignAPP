package com.fzucai.yunsign.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzucai.yunsign.entity.Putsign;
import com.fzucai.yunsign.service.PutSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/putsign")
public class PutSignHandler {
    @Autowired
    PutSignService putSignService;

    //根据课程id查找课程信息
    @PostMapping("/findById")
    public Putsign findById(@RequestBody JSONObject jsonObject){
        String a =jsonObject.get("putsignid").toString();
        Integer putsignid = Integer.valueOf(a);
        Putsign putsign = putSignService.findPutSignById(putsignid);
        return putsign;
    }

    @GetMapping("/findsignBycourseId")
    public List<Putsign> findsignBycourseId(@RequestParam Integer courseid) {return putSignService.findsignBycourseId(courseid);}

    //查询
    @GetMapping("/findAll")
    public List<Putsign> findAll(){return putSignService.findAll();}

    //新建
    @RequestMapping(value = "/addPutSign", method = RequestMethod.POST)
    public String addPutSign(@RequestBody Putsign putSign){
        return putSignService.addPutSign(putSign);
    }

    //删除
    @DeleteMapping("/deletePutSign")
    public String deletePutSign(@RequestParam Integer putsignid){
        return putSignService.deletePutSign(putsignid);
    }

    //修改
    @RequestMapping(value = "/updatePutSign", method = RequestMethod.POST)
    public String updatePutSign(@RequestBody Putsign putsign){
        return putSignService.updatePutSign(putsign);
    }
}