package com.fzucai.yunsign.controller;

import com.fzucai.yunsign.entity.Sign;
import com.fzucai.yunsign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sign")
public class SignHandler {
    @Autowired
    private SignService signService;
    private Sign sign;

    @GetMapping("/findAll")
    public List<Sign> findAll() { return signService.findAll();}

    //新建签到记录
    @RequestMapping(value = "/addSign", method = RequestMethod.POST)
    public String addSign(@RequestBody Sign sign){
        return signService.addSign(sign);
    }

    //删除
    @PostMapping("/deleteSign")
    public String deleteSign(@RequestParam Integer signid){
        return signService.deleteSign(signid);
    }
}
