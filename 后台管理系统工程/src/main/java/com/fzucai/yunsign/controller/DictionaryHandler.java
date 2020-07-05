package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.Dictionary;
import com.fzucai.yunsign.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryHandler {
    @Autowired
    DictionaryService dictionaryService;

    //增加
    @RequestMapping(value = "/addDictionary", method = RequestMethod.POST)
    public String addDictionary(@RequestBody Dictionary dictionary){
        return dictionaryService.addDictionary(dictionary);
    }

    //删除
    @PostMapping("/deleteDictionary")
    public String deleteDictionary(@RequestParam Integer dictionaryid){
        return dictionaryService.deleteDictionary(dictionaryid);
    }

    //修改
    @RequestMapping(value = "/updateDictionary", method = RequestMethod.POST)
    public String updateDictionary(@RequestBody Dictionary dictionary){
        return dictionaryService.updateDictionary(dictionary);
    }

    @GetMapping("/findAll")
    public List<Dictionary> findAll(){
        return dictionaryService.findAll();
    }
}
