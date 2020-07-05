package com.fzucai.yunsign.controller;


import com.fzucai.yunsign.entity.Power;
import com.fzucai.yunsign.repository.PowerRepository;
import com.fzucai.yunsign.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/power")
public class PowerHandler {
    @Autowired
    private PowerRepository powerRepository;

    @GetMapping("/findAll")
    public List<Power> findAll(){
        return powerRepository.findAll();
    }
}
